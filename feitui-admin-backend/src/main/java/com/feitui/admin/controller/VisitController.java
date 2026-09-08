package com.feitui.admin.controller;

import com.feitui.admin.common.R;
import com.feitui.admin.dto.TrackRequest;
import com.feitui.admin.service.VisitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visit")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping("/report")
    public R<Void> report(@RequestBody TrackRequest req, HttpServletRequest request) {
        req.setIp(clientIp(request));
        visitService.report(req);
        return R.ok();
    }

    private String clientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isBlank()) {
            return ip.split(",")[0].trim();
        }
        ip = request.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(ip) || "127.0.0.1".equals(ip)) {
            return "127.0.0.1";
        }
        return ip;
    }
}