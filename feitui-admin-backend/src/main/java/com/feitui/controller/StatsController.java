package com.feitui.controller;

import com.feitui.common.R;
import com.feitui.entity.Stats;
import com.feitui.service.StatsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/list")
    public R<List<Stats>> listAll() {
        List<Stats> list = statsService.listAll();
        return R.ok(list);
    }

    @GetMapping("/key/{statKey}")
    public R<Stats> getByKey(@PathVariable String statKey) {
        Stats stats = statsService.getByKey(statKey);
        if (stats == null) {
            return R.fail("统计数据不存在");
        }
        return R.ok(stats);
    }

    @PostMapping
    public R<Stats> save(@RequestBody Stats stats) {
        Stats saved = statsService.save(stats);
        return R.ok(saved);
    }

    @PutMapping
    public R<Void> update(@RequestBody Stats stats) {
        boolean success = statsService.update(stats);
        return success ? R.ok() : R.fail("更新失败");
    }
}
