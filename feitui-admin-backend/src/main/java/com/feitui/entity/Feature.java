package com.feitui.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Feature {
    private Long id;
    private String title;
    private String description;
    private String icon;
    private String tags;
    private Integer sort;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
