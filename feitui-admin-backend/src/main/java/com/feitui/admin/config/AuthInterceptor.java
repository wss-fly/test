package com.feitui.admin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feitui.admin.common.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final TokenStore tokenStore;
    private final ObjectMapper objectMapper;

    public AuthInterceptor(TokenStore tokenStore, ObjectMapper objectMapper) {
        this.tokenStore = tokenStore;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行跨域预检
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        TokenStore.TokenInfo info = (token == null) ? null : tokenStore.get(token);
        if (info == null) {
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            objectMapper.writeValue(response.getWriter(), R.fail(401, "请先登录"));
            return false;
        }
        request.setAttribute("loginUser", info);
        // 用户管理接口仅超级管理员可访问
        if (request.getRequestURI().startsWith("/api/admin-user") && !"SUPER".equals(info.role)) {
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            objectMapper.writeValue(response.getWriter(), R.fail(403, "无权限，仅超级管理员可操作"));
            return false;
        }
        return true;
    }
}