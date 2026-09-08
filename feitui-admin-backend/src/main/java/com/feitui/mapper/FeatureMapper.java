package com.feitui.mapper;

import com.feitui.entity.Feature;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface FeatureMapper {
    List<Feature> selectAll();
    Feature selectById(@Param("id") Long id);
    int insert(Feature feature);
    int updateById(Feature feature);
    int deleteById(@Param("id") Long id);
}
