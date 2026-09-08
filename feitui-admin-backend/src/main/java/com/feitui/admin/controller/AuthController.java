package com.feitui.admin.controller;

import com.feitui.admin.common.R;
import com.feitui.admin.config.TokenStore;
import com.feitui.admin.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final TokenStore tokenStore;

    public AuthController(AuthService authService, TokenStore tokenStore) {
        this.authService = authService;
        this.tokenStore = tokenStore;
    }

    @PostMapping("/login")
    public R<Map<String, Object>> login(@RequestBody Map<String, String> body) {
        try {
            Map<String, Object> data = authService.login(body.get("username"), body.get("password"), body.get("loginType"));
            return R.ok("登录成功", data);
        } catch (RuntimeException e) {
            return R.fail(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public R<Void> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            token = token.replace("Bearer ", "");
            tokenStore.remove(token);
        }
        return R.ok();
    }

    @GetMapping("/info")
    public R<Map<String, Object>> info(HttpServletRequest request) {
        TokenStore.TokenInfo info = (TokenStore.TokenInfo) request.getAttribute("loginUser");
        Map<String, Object> m = new HashMap<>();
        m.put("username", info.username);
        m.put("nickname", info.nickname);
        return R.ok(m);
    }

    @PostMapping("/change-password")
    public R<Void> changePassword(@RequestBody Map<String, String> body, HttpServletRequest request) {
        try {
            TokenStore.TokenInfo info = (TokenStore.TokenInfo) request.getAttribute("loginUser");
            authService.changePassword(info.userId, body.get("oldPassword"), body.get("newPassword"));
            return R.ok();
        } catch (RuntimeException e) {
            return R.fail(e.getMessage());
        }
    }
}