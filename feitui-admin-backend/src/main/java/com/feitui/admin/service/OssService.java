package com.feitui.admin.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.SetBucketCORSRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 阿里云 OSS 直传：仅在后端持有 AccessKey，为前端生成预签名上传 URL，
 * 前端拿到后用 PUT 直传文件，避免密钥暴露。
 */
@Service
public class OssService {

    @Value("${oss.endpoint:}")
    private String endpoint;
    @Value("${oss.bucket:}")
    private String bucket;
    @Value("${oss.access-key-id:}")
    private String accessKeyId;
    @Value("${oss.access-key-secret:}")
    private String accessKeySecret;
    @Value("${oss.public-domain:}")
    private String publicDomain;
    @Value("${oss.presign-expire-seconds:300}")
    private long presignExpireSeconds;

    private static final DateTimeFormatter MONTH = DateTimeFormatter.ofPattern("yyyyMM");
    private static final java.util.Set<String> VIDEO_EXTS = java.util.Set.of("mp4", "webm", "mov", "m4v", "avi", "ogg");

    public boolean configured() {
        return StringUtils.hasText(endpoint) && StringUtils.hasText(bucket)
                && StringUtils.hasText(accessKeyId) && StringUtils.hasText(accessKeySecret);
    }

    /**
     * 为 Bucket 配置跨域(CORS)规则。每个来源允许 GET/HEAD/PUT 等，
     * 保证后台(5174)能直传、前台(5173)能读时长/抽封面。
     */
    public void setCors(List<String> origins) {
        if (!configured()) {
            throw new IllegalStateException("请先在 application.yml 配置阿里云 OSS");
        }
        List<SetBucketCORSRequest.CORSRule> rules = new ArrayList<>();
        for (String origin : origins) {
            SetBucketCORSRequest.CORSRule rule = new SetBucketCORSRequest.CORSRule();
            rule.addAllowdOrigin(origin);
            rule.addAllowedMethod("GET");
            rule.addAllowedMethod("HEAD");
            rule.addAllowedMethod("PUT");
            rule.addAllowedMethod("POST");
            rule.addAllowedMethod("DELETE");
            rule.addAllowedHeader("*");
            rule.setMaxAgeSeconds(3600);
            rules.add(rule);
        }
        SetBucketCORSRequest req = new SetBucketCORSRequest(bucket);
        req.setCorsRules(rules);

        OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            client.setBucketCORS(req);
        } finally {
            client.shutdown();
        }
    }

    /**
     * 生成某个待上传文件的预签名 URL 和最终公网访问 URL。
     * 文件名仅用于取扩展名分类（视频/封面）。
     */
    public Map<String, String> createUploadUrl(String filename) {
        if (!configured()) {
            throw new IllegalStateException("请先在 application.yml 配置阿里云 OSS（endpoint/bucket/access-key-id/access-key-secret）");
        }
        String ext = ext(filename);
        String category = VIDEO_EXTS.contains(ext) ? "videos" : "covers";
        String key = category + "/" + LocalDateTime.now().format(MONTH) + "/" + UUID.randomUUID().toString().replace("-", "") + "." + ext;
        // Content-Type 写入签名并回传前端，上传时带上同名 header，OSS 才能存成正确 MIME（video 前台才能播）
        String contentType = contentType(ext);

        URL uploadUrl = buildClientPresignedUrl(key, contentType);
        String base = StringUtils.hasText(publicDomain)
                ? "https://" + publicDomain
                : "https://" + bucket + "." + endpoint;

        Map<String, String> result = new HashMap<>();
        result.put("uploadUrl", uploadUrl.toString());
        result.put("objectUrl", base + "/" + key);
        result.put("key", key);
        result.put("contentType", contentType);
        return result;
    }

    private URL buildClientPresignedUrl(String key, String contentType) {
        OSS client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            java.util.Date expiration = new java.util.Date(System.currentTimeMillis() + presignExpireSeconds * 1000);
            GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucket, key);
            req.setMethod(HttpMethod.PUT);
            req.setExpiration(expiration);
            req.setContentType(contentType);
            return client.generatePresignedUrl(req);
        } finally {
            client.shutdown();
        }
    }

    private static String ext(String filename) {
        if (filename == null) return "mp4";
        int idx = filename.lastIndexOf('.');
        if (idx < 0 || idx == filename.length() - 1) return "mp4";
        return filename.substring(idx + 1).toLowerCase();
    }

    private static String contentType(String ext) {
        switch (ext) {
            case "mp4": return "video/mp4";
            case "webm": return "video/webm";
            case "mov": return "video/quicktime";
            case "m4v": return "video/x-m4v";
            case "avi": return "video/x-msvideo";
            case "ogg": return "video/ogg";
            case "jpg": case "jpeg": return "image/jpeg";
            case "png": return "image/png";
            case "webp": return "image/webp";
            case "gif": return "image/gif";
            default: return "application/octet-stream";
        }
    }
}