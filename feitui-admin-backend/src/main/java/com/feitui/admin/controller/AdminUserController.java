package com.feitui.admin.controller;

import com.feitui.admin.common.R;
import com.feitui.admin.config.TokenStore;
import com.feitui.admin.entity.AdminUser;
import com.feitui.admin.service.AdminUserService;
import com.github.pagehelper.PageInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin-user")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @GetMapping("/page")
    public R<PageInfo<AdminUser>> page(@RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(required = false) String keyword) {
        return R.ok(adminUserService.page(page, Math.min(size, 100), keyword));
    }

    @PostMapping
    public R<Void> create(@RequestBody Map<String, String> body) {
        try {
            adminUserService.create(body.get("username"), body.get("nickname"),
                    body.get("password"), body.get("role"),
                    body.get("status") == null ? null : Integer.valueOf(body.get("status")));
            return R.ok();
        } catch (RuntimeException e) {
            return R.fail(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            adminUserService.update(id, body.get("nickname"), body.get("role"),
                    body.get("status") == null ? null : Integer.valueOf(body.get("status")));
            return R.ok();
        } catch (RuntimeException e) {
            return R.fail(e.getMessage());
        }
    }

    @PutMapping("/{id}/password")
    public R<Void> resetPassword(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            adminUserService.resetPassword(id, body.get("password"));
            return R.ok();
        } catch (RuntimeException e) {
            return R.fail(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id, HttpServletRequest request) {
        try {
            TokenStore.TokenInfo info = (TokenStore.TokenInfo) request.getAttribute("loginUser");
            adminUserService.delete(id, info.userId);
            return R.ok();
        } catch (RuntimeException e) {
            return R.fail(e.getMessage());
        }
    }
}