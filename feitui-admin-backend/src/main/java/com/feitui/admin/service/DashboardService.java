package com.feitui.admin.service;

import com.feitui.admin.entity.Contact;
import com.feitui.admin.mapper.AdminContactMapper;
import com.feitui.admin.mapper.VisitMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final VisitMapper visitMapper;
    private final AdminContactMapper AdminContactMapper;
    private final VisitService visitService;

    @Value("${admin.online-minutes:5}")
    private int onlineMinutes;

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public DashboardService(VisitMapper visitMapper, AdminContactMapper AdminContactMapper, VisitService visitService) {
        this.visitMapper = visitMapper;
        this.AdminContactMapper = AdminContactMapper;
        this.visitService = visitService;
    }

    public Map<String, Object> overview() {
        Map<String, Object> m = new LinkedHashMap<>();
        long totalVisits = visitMapper.totalPv() == null ? 0 : visitMapper.totalPv();
        long totalUv = visitMapper.totalUv() == null ? 0 : visitMapper.totalUv();
        LocalDate today = LocalDate.now();
        Integer dp = visitMapper.dayPv(today);
        Long todayActive = visitMapper.activeUv(today.atStartOfDay());
        LocalDate yesterday = today.minusDays(1);
        Integer ydp = visitMapper.dayPv(yesterday);

        long todayContacts = AdminContactMapper.todayCount(today.toString()) == null ? 0 : AdminContactMapper.todayCount(today.toString());
        long totalContacts = AdminContactMapper.totalCount() == null ? 0 : AdminContactMapper.totalCount();

        m.put("totalVisits", totalVisits);
        m.put("totalUV", totalUv);
        m.put("todayVisits", dp == null ? 0 : dp);
        m.put("todayUV", todayActive == null ? 0 : todayActive);
        m.put("yesterdayVisits", ydp == null ? 0 : ydp);
        m.put("onlineUsers", visitService.onlineCount());
        m.put("todayContacts", todayContacts);
        m.put("totalContacts", totalContacts);
        return m;
    }

    public List<Map<String, Object>> trend(int days) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(days - 1);
        List<Map<String, Object>> dayRows = visitMapper.recentDays(start, end);
        Map<String, Map<String, Object>> byDate = new HashMap<>();
        for (Map<String, Object> row : dayRows) {
            String d = String.valueOf(row.get("date"));
            Map<String, Object> v = new LinkedHashMap<>();
            v.put("pv", row.get("pv") == null ? 0 : row.get("pv"));
            v.put("uv", row.get("uv") == null ? 0 : row.get("uv"));
            v.put("contacts", 0);
            byDate.put(d, v);
        }
        // 覆盖每日访客数：改用按访客去重的活跃访客数(避免 t_visit_day.uv 因会话重复累加而虚高)
        List<Map<String, Object>> uvRows = visitMapper.activeUvByDay(start.atStartOfDay(), end.plusDays(1).atStartOfDay());
        for (Map<String, Object> row : uvRows) {
            String d = String.valueOf(row.get("date"));
            Map<String, Object> v = byDate.computeIfAbsent(d, k -> {
                Map<String, Object> m0 = new LinkedHashMap<>();
                m0.put("pv", 0);
                m0.put("uv", 0);
                m0.put("contacts", 0);
                return m0;
            });
            v.put("uv", row.get("uv") == null ? 0 : row.get("uv"));
        }
        // 咨询量按天分组
        List<Contact> recent = AdminContactMapper.recentList(2000);
        Map<String, Long> contactByDate = recent.stream()
                .filter(c -> c.getCreateTime() != null)
                .collect(Collectors.groupingBy(c -> c.getCreateTime().toLocalDate().toString(), Collectors.counting()));
        for (Map.Entry<String, Long> e : contactByDate.entrySet()) {
            byDate.computeIfAbsent(e.getKey(), k -> {
                Map<String, Object> v = new LinkedHashMap<>();
                v.put("pv", 0);
                v.put("uv", 0);
                v.put("contacts", 0);
                return v;
            }).put("contacts", e.getValue().intValue());
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {
            Map<String, Object> row = byDate.get(d.toString());
            if (row == null) {
                row = new LinkedHashMap<>();
                row.put("pv", 0);
                row.put("uv", 0);
                row.put("contacts", 0);
            }
            Map<String, Object> out = new LinkedHashMap<>();
            out.put("date", d.format(FMT));
            out.put("pv", row.get("pv"));
            out.put("uv", row.get("uv"));
            out.put("contacts", row.get("contacts"));
            result.add(out);
        }
        return result;
    }

    public List<Map<String, Object>> sourceAnalysis() {
        List<Map<String, Object>> list = visitMapper.groupByChannel(LocalDateTime.now().minusMinutes(onlineMinutes * 24 * 30L));
        return list == null ? new ArrayList<>() : list;
    }

    public List<Map<String, Object>> onlineList() {
        List<Map<String, Object>> list = visitMapper.onlineList(LocalDateTime.now().minusMinutes(onlineMinutes));
        return list == null ? new ArrayList<>() : list;
    }
}