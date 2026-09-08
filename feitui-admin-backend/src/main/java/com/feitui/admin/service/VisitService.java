package com.feitui.admin.service;

import com.feitui.admin.dto.TrackRequest;
import com.feitui.admin.entity.Visit;
import com.feitui.admin.mapper.VisitMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class VisitService {

    private final VisitMapper visitMapper;

    @Value("${admin.online-minutes:5}")
    private int onlineMinutes;

    public VisitService(VisitMapper visitMapper) {
        this.visitMapper = visitMapper;
    }

    public void report(TrackRequest req) {
        if (!StringUtils.hasText(req.getSessionId())) return;
        String event = StringUtils.hasText(req.getEvent()) ? req.getEvent() : "pageview";

        Visit existing = visitMapper.findBySession(req.getSessionId());
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();

        if (existing == null) {
            // 新会话
            Visit v = new Visit();
            v.setVisitorId(req.getVisitorId() != null ? req.getVisitorId() : req.getSessionId());
            v.setSessionId(req.getSessionId());
            v.setIp(req.getIp());
            v.setReferrer(req.getReferrer());
            v.setSource(req.getSource());
            v.setChannel(resolveChannel(req.getSource(), req.getReferrer()));
            v.setDevice(resolveDevice(req.getUa()));
            v.setBrowser(resolveBrowser(req.getUa()));
            v.setOs(resolveOs(req.getUa()));
            v.setLandingPage(req.getPage());
            v.setFirstTime(now);
            v.setLastActive(now);
            v.setPv(1);
            visitMapper.insert(v);
            visitMapper.upsertDay(today, 1, 1);
        } else if ("heartbeat".equals(event)) {
            visitMapper.updateHeartbeat(req.getSessionId(), now);
        } else {
            // pageview
            visitMapper.bumpPv(req.getSessionId(), now);
            visitMapper.upsertDay(today, 1, 0);
        }
    }

    public long onlineCount() {
        Long c = visitMapper.onlineCount(LocalDateTime.now().minusMinutes(onlineMinutes));
        return c == null ? 0 : c;
    }

    private String resolveChannel(String source, String referrer) {
        String s = source == null ? "" : source.toLowerCase(Locale.ROOT);
        if (containsAny(s, "facebook", "fb", "meta")) return "Facebook";
        if (s.contains("instagram")) return "Instagram";
        if (containsAny(s, "google", "bing", "baidu", "sogou", "360", "yandex", "so.com")) return "搜索引擎";
        if (containsAny(s, "telegram", "t.me")) return "Telegram";
        if (containsAny(s, "whatsapp", "wa.me")) return "WhatsApp";
        if (containsAny(s, "twitter", "x.com", "t.co")) return "Twitter";
        if (s.contains("tiktok")) return "TikTok";
        if (containsAny(s, "wechat", "weixin")) return "微信";
        String r = referrer == null ? "" : referrer.toLowerCase(Locale.ROOT);
        if (r.contains("facebook")) return "Facebook";
        if (r.contains("instagram")) return "Instagram";
        if (containsAny(r, "google", "bing", "baidu", "sogou")) return "搜索引擎";
        if (containsAny(r, "telegram", "t.me")) return "Telegram";
        if (containsAny(r, "whatsapp", "wa.me")) return "WhatsApp";
        if (containsAny(r, "twitter", "x.com", "t.co")) return "Twitter";
        if (r.contains("tiktok")) return "TikTok";
        return "直接访问";
    }

    private boolean containsAny(String s, String... keys) {
        for (String k : keys) {
            if (s.contains(k)) return true;
        }
        return false;
    }

    private String resolveDevice(String ua) {
        if (ua == null) return "desktop";
        String u = ua.toLowerCase(Locale.ROOT);
        if (u.contains("tablet") || u.contains("ipad")) return "tablet";
        if (u.contains("mobile") || u.contains("android") && u.contains("mobile")) return "mobile";
        if (u.contains("iphone") || u.contains("android")) return "mobile";
        return "desktop";
    }

    private String resolveBrowser(String ua) {
        if (ua == null) return "其他";
        String u = ua.toLowerCase(Locale.ROOT);
        // 先匹配国产/特殊浏览器，避免其UA含Chrome/Safari而被误判
        if (u.contains("quark")) return "夸克";
        if (u.contains("micromessenger") || u.contains("wechat")) return "微信";
        if (u.contains("edg")) return "Edge";
        if (u.contains("opera") || u.contains("opr")) return "Opera";
        if (u.contains("qqbrowser") || u.contains("tencenttraveler")) return "QQ浏览器";
        if (u.contains("ucbrowser") || u.contains("ubrowser")) return "UC浏览器";
        if (u.contains("bidubrowser") || u.contains("baiduboxapp")) return "百度浏览器";
        if (u.contains("360se") || u.contains("360ee")) return "360浏览器";
        if (u.contains("metasr") || u.contains("sogou")) return "搜狗浏览器";
        if (u.contains("brave")) return "Brave";
        if (u.contains("chrome") || u.contains("crios")) return "Chrome";
        if (u.contains("firefox") || u.contains("fxios")) return "Firefox";
        if (u.contains("safari")) return "Safari";
        return "其他";
    }

    private String resolveOs(String ua) {
        if (ua == null) return "其他";
        String u = ua.toLowerCase(Locale.ROOT);
        if (u.contains("windows")) return "Windows";
        if (u.contains("android")) return "Android";
        if (u.contains("iphone") || u.contains("ios") || u.contains("ipad")) return "iOS";
        if (u.contains("mac")) return "MacOS";
        if (u.contains("linux")) return "Linux";
        return "其他";
    }
}