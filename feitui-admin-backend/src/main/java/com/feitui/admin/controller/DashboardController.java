package com.feitui.admin.controller;

import com.feitui.admin.common.R;
import com.feitui.admin.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/overview")
    public R<Map<String, Object>> overview() {
        return R.ok(dashboardService.overview());
    }

    @GetMapping("/trend")
    public R<List<Map<String, Object>>> trend(@RequestParam(defaultValue = "7") int days) {
        return R.ok(dashboardService.trend(days));
    }

    @GetMapping("/source")
    public R<List<Map<String, Object>>> source() {
        return R.ok(dashboardService.sourceAnalysis());
    }

    @GetMapping("/online")
    public R<List<Map<String, Object>>> online() {
        return R.ok(dashboardService.onlineList());
    }
}