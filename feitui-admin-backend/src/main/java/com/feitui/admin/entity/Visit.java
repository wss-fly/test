package com.feitui.admin.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Visit {
    private Long id;
    private String visitorId;
    private String sessionId;
    private String ip;
    private String referrer;
    private String source;
    private String channel;
    private String device;
    private String browser;
    private String os;
    private String landingPage;
    private LocalDateTime firstTime;
    private LocalDateTime lastActive;
    private Integer pv;
}