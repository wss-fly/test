package com.feitui.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Video {
    private Long id;
    private String title;
    private String description;
    private String videoUrl;
    private String coverImage;
    private String duration;
    private Integer sort;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
