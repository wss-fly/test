package com.feitui.mapper;

import com.feitui.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface VideoMapper {
    List<Video> selectAll();
    Video selectById(@Param("id") Long id);
    int insert(Video video);
    int updateById(Video video);
    int deleteById(@Param("id") Long id);
}
