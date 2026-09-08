package com.feitui.admin.service;

import com.feitui.admin.config.TokenStore;
import com.feitui.admin.entity.AdminUser;
import com.feitui.admin.mapper.AdminUserMapper;
import com.feitui.admin.util.Md5Util;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final AdminUserMapper adminUserMapper;
    private final TokenStore tokenStore;

    public AuthService(AdminUserMapper adminUserMapper, TokenStore tokenStore) {
        this.adminUserMapper = adminUserMapper;
        this.tokenStore = tokenStore;
    }

    public Map<String, Object> login(String username, String password, String loginType) {
        AdminUser user = adminUserMapper.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        if (user.getStatus() == null || user.getStatus() != 1) {
            throw new RuntimeException("该账号已被禁用");
        }
        if (!user.getPassword().equals(Md5Util.md5(password))) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 校验登录入口与账号角色匹配：super=超级管理员，admin=管理员
        String expectedRole = "super".equalsIgnoreCase(loginType) ? "SUPER" : "ADMIN";
        if ("SUPER".equalsIgnoreCase(user.getRole()) && !"super".equalsIgnoreCase(loginType)) {
            throw new RuntimeException("该账号是超级管理员，请切换到「超级管理员登录」");
        }
        if ("ADMIN".equalsIgnoreCase(user.getRole()) && "super".equalsIgnoreCase(loginType)) {
            throw new RuntimeException("该账号是管理员，请切换到「管理员登录」");
        }
        adminUserMapper.updateLastLogin(user.getId());
        String token = tokenStore.createToken(user.getId(), user.getUsername(), user.getNickname(), user.getRole());
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("username", user.getUsername());
        result.put("nickname", user.getNickname());
        result.put("role", user.getRole());
        if (result.get("nickname") == null) {
            result.put("nickname", user.getUsername());
        }
        return result;
    }

    /**
     * 仅超级管理员本人可通过验证原密码修改自己的密码
     */
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        AdminUser user = adminUserMapper.findById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        if (!"SUPER".equalsIgnoreCase(user.getRole())) {
            throw new RuntimeException("仅超级管理员本人可修改密码");
        }
        if (newPassword == null || newPassword.length() < 6) {
            throw new RuntimeException("密码长度不能少于6位");
        }
        if (oldPassword == null || !user.getPassword().equals(Md5Util.md5(oldPassword))) {
            throw new RuntimeException("原密码错误");
        }
        adminUserMapper.updatePassword(userId, Md5Util.md5(newPassword));
    }
}