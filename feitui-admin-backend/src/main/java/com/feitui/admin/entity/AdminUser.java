package com.feitui.admin.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdminUser {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String role;
    private Integer status;
    private LocalDateTime lastLoginTime;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}