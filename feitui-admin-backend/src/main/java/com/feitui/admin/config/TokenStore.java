package com.feitui.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单的内存 Token 管理（单点登录场景足够）
 */
@Component
public class TokenStore {

    public static class TokenInfo {
        public Long userId;
        public String username;
        public String nickname;
        public String role;
        public LocalDateTime expireAt;
    }

    private final Map<String, TokenInfo> store = new ConcurrentHashMap<>();

    @Value("${admin.auth.token-expire-hours:12}")
    private int expireHours;

    public String createToken(Long userId, String username, String nickname, String role) {
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenInfo info = new TokenInfo();
        info.userId = userId;
        info.username = username;
        info.nickname = nickname;
        info.role = role;
        info.expireAt = LocalDateTime.now().plusHours(expireHours);
        store.put(token, info);
        return token;
    }

    public TokenInfo get(String token) {
        TokenInfo info = store.get(token);
        if (info == null) return null;
        if (info.expireAt.isBefore(LocalDateTime.now())) {
            store.remove(token);
            return null;
        }
        return info;
    }

    public void remove(String token) {
        store.remove(token);
    }
}