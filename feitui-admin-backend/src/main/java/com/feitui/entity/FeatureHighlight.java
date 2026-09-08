package com.feitui.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FeatureHighlight {
    private Long id;
    private String name;
    private String color;
    private Integer sort;
    private Integer status;
    private LocalDateTime createTime;
}
