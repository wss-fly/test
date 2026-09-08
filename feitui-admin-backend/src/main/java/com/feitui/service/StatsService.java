package com.feitui.service;

import com.feitui.entity.Stats;
import java.util.List;

public interface StatsService {
    List<Stats> listAll();
    Stats getByKey(String statKey);
    Stats save(Stats stats);
    boolean update(Stats stats);
}
