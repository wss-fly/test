package com.feitui.admin.dto;

import lombok.Data;

@Data
public class TrackRequest {
    // pageview | heartbeat
    private String event;
    private String visitorId;
    private String sessionId;
    // 当前页面路径
    private String page;
    // document.referrer
    private String referrer;
    // utm_source 等
    private String source;
    private String ua;
    private String ip;
}