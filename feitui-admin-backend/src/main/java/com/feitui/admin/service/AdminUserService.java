package com.feitui.admin.service;

import com.feitui.admin.entity.AdminUser;
import com.feitui.admin.mapper.AdminUserMapper;
import com.feitui.admin.util.Md5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminUserService {

    private final AdminUserMapper adminUserMapper;

    public AdminUserService(AdminUserMapper adminUserMapper) {
        this.adminUserMapper = adminUserMapper;
    }

    public PageInfo<AdminUser> page(int page, int size, String keyword) {
        PageHelper.startPage(page, size);
        List<AdminUser> list = adminUserMapper.page(keyword);
        return new PageInfo<>(list);
    }

    public void create(String username, String nickname, String password, String role, Integer status) {
        if (username == null || username.isBlank()) {
            throw new RuntimeException("用户名不能为空");
        }
        if (password == null || password.length() < 6) {
            throw new RuntimeException("密码长度不能少于6位");
        }
        if (adminUserMapper.countByUsername(username) > 0) {
            throw new RuntimeException("用户名已存在");
        }
        // 超级管理员全局唯一：不允许再创建超级管理员账号
        if ("SUPER".equalsIgnoreCase(role)) {
            throw new RuntimeException("超级管理员账号唯一，不可创建");
        }
        AdminUser user = new AdminUser();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(Md5Util.md5(password));
        user.setRole(role == null || role.isBlank() ? "ADMIN" : role);
        user.setStatus(status == null ? 1 : status);
        // 复用最小空闲 ID：删掉中间账号后的空号会被新账号顶上，保证 ID 从 1 连续、按顺序递增
        for (int attempt = 0; attempt < 5; attempt++) {
            int nextId = 1;
            Set<Integer> used = new HashSet<>(adminUserMapper.selectIds());
            while (used.contains(nextId)) nextId++;
            user.setId((long) nextId);
            try {
                adminUserMapper.insert(user);
                return;
            } catch (DuplicateKeyException e) {
                // 并发下两人同时抢占同一空号，重试重新计算
            }
        }
        throw new RuntimeException("创建失败，请重试");
    }

    public void update(Long id, String nickname, String role, Integer status) {
        AdminUser target = adminUserMapper.findById(id);
        if (target == null) {
            throw new RuntimeException("用户不存在");
        }
        // 超级管理员不可修改
        if ("SUPER".equals(target.getRole())) {
            throw new RuntimeException("超级管理员账号不可修改");
        }
        if (role == null || role.isBlank()) {
            role = "ADMIN";
        }
        // 防止把普通账号升级成超级管理员（仅允许一个）
        if ("SUPER".equalsIgnoreCase(role)) {
            throw new RuntimeException("超级管理员账号唯一，不可创建");
        }
        adminUserMapper.updateInfo(id, nickname, role, status == null ? 1 : status);
    }

    public void resetPassword(Long id, String password) {
        if (password == null || password.length() < 6) {
            throw new RuntimeException("密码长度不能少于6位");
        }
        AdminUser target = adminUserMapper.findById(id);
        if (target == null) {
            throw new RuntimeException("用户不存在");
        }
        // 超级管理员密码不可重置（如需修改由数据库维护）
        if ("SUPER".equals(target.getRole())) {
            throw new RuntimeException("超级管理员账号不可修改");
        }
        adminUserMapper.updatePassword(id, Md5Util.md5(password));
    }

    @Transactional
    public void delete(Long id, Long operatorId) {
        if (id.equals(operatorId)) {
            throw new RuntimeException("不能删除当前登录账号");
        }
        AdminUser target = adminUserMapper.findById(id);
        if (target == null) {
            throw new RuntimeException("用户不存在");
        }
        // 超级管理员不可删除
        if ("SUPER".equals(target.getRole())) {
            throw new RuntimeException("超级管理员账号不可删除");
        }
        adminUserMapper.deleteById(id);
        // 删除后紧凑重排：后面所有用户 id 前移，保证 ID 连续无空洞
        adminUserMapper.shiftIdDown(id);
    }
}