package com.feitui.mapper;

import com.feitui.entity.Stats;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StatsMapper {
    List<Stats> selectAll();
    Stats selectByKey(@Param("statKey") String statKey);
    int insert(Stats stats);
    int updateByKey(Stats stats);
}
