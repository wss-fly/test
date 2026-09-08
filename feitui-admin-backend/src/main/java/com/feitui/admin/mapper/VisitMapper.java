package com.feitui.admin.mapper;

import com.feitui.admin.entity.Visit;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface VisitMapper {
    Visit findBySession(@Param("sessionId") String sessionId);
    int insert(Visit visit);
    int updateHeartbeat(@Param("sessionId") String sessionId, @Param("lastActive") java.time.LocalDateTime lastActive);
    int bumpPv(@Param("sessionId") String sessionId, @Param("lastActive") java.time.LocalDateTime lastActive);
    Long totalPv();
    Long totalUv();
    // 在线人数(最近N分钟内活跃的会话)
    Long onlineCount(@Param("since") java.time.LocalDateTime since);
    // 当日PV/UV
    Integer dayPv(@Param("date") LocalDate date);
    Integer dayUv(@Param("date") LocalDate date);
    // 某日活跃的独立访客数(按访客去重，避免同一人多会话/多次访问重复计)
    Long activeUv(@Param("since") java.time.LocalDateTime since);
    // 区间内按"日"统计的活跃独立访客数(用于趋势图)
    List<Map<String, Object>> activeUvByDay(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
    // 渠道来源分布
    List<Map<String, Object>> groupByChannel(@Param("since") java.time.LocalDateTime since);
    // 最近N天趋势
    List<Map<String, Object>> recentDays(@Param("start") LocalDate start, @Param("end") LocalDate end);
    // 日汇总 upsert
    int upsertDay(@Param("date") LocalDate date, @Param("pv") int pv, @Param("uv") int uv);
    // 在线访客明细(用于实时表格)
    List<Map<String, Object>> onlineList(@Param("since") java.time.LocalDateTime since);
}