package com.feitui.mapper;

import com.feitui.entity.FeatureHighlight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface FeatureHighlightMapper {
    List<FeatureHighlight> selectAll();
    int insert(FeatureHighlight highlight);
    int updateById(FeatureHighlight highlight);
    int deleteById(@Param("id") Long id);
}
