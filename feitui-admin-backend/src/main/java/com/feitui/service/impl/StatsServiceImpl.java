package com.feitui.service.impl;

import com.feitui.entity.Stats;
import com.feitui.mapper.StatsMapper;
import com.feitui.service.StatsService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {

    private final StatsMapper statsMapper;

    public StatsServiceImpl(StatsMapper statsMapper) {
        this.statsMapper = statsMapper;
    }

    @Override
    public List<Stats> listAll() {
        return statsMapper.selectAll();
    }

    @Override
    public Stats getByKey(String statKey) {
        return statsMapper.selectByKey(statKey);
    }

    @Override
    public Stats save(Stats stats) {
        statsMapper.insert(stats);
        return stats;
    }

    @Override
    public boolean update(Stats stats) {
        return statsMapper.updateByKey(stats) > 0;
    }
}
