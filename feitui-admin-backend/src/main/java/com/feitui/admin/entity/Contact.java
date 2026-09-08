package com.feitui.admin.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Contact {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String company;
    private String platform;
    private String requirement;
    private Integer status;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}