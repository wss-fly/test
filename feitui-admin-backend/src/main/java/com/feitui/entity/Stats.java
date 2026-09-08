package com.feitui.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Stats {
    private Long id;
    private String statKey;
    private String statValue;
    private String statLabel;
    private String description;
    private Integer sort;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
