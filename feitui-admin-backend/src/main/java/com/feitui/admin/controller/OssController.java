package com.feitui.admin.controller;

import com.feitui.admin.common.R;
import com.feitui.admin.service.OssService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/oss")
public class OssController {

    private final OssService ossService;

    public OssController(OssService ossService) {
        this.ossService = ossService;
    }

    // 为前端生成直传 OSS 的预签名 URL。前端拿到 uploadUrl 后用 PUT 上传文件，objectUrl 填入视频/封面地址
    @PostMapping("/presign")
    public R<Map<String, String>> presign(@RequestBody Map<String, String> body) {
        try {
            return R.ok(ossService.createUploadUrl(body.get("filename")));
        } catch (IllegalStateException e) {
            return R.fail(e.getMessage());
        }
    }

    // 配置 Bucket 跨域(CORS)。origins 缺省为本地前后台地址；上线后可传生产域名
    @PostMapping("/cors")
    public R<Void> cors(@RequestBody(required = false) Map<String, Object> body) {
        List<String> origins = new ArrayList<>();
        if (body != null && body.get("origins") instanceof List<?> list) {
            for (Object o : list) if (o != null && !o.toString().isBlank()) origins.add(o.toString().trim());
        }
        if (origins.isEmpty()) {
            origins.add("http://localhost:5173");
            origins.add("http://localhost:5174");
        }
        try {
            ossService.setCors(origins);
            return R.ok();
        } catch (IllegalStateException e) {
            return R.fail(e.getMessage());
        } catch (Exception e) {
            return R.fail("CORS 配置失败：" + e.getMessage());
        }
    }
}