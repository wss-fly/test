package com.feitui.mapper;

import com.feitui.entity.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface VideoMapper {
    List<Video> selectAll();
    // 后台管理用：不分启停状态，全部返回
    List<Video> selectAllForAdmin();
    Video selectById(@Param("id") Long id);
    int insert(Video video);
    int updateById(Video video);
    int deleteById(@Param("id") Long id);
    // 已占用的 id 列表，用于新记录分配最小空闲 id（保证 id 从 1 连续）
    List<Integer> selectIds();
    // 删除后紧凑重排：把 id 大于被删 id 的所有行往前挪一位
    int shiftIdDown(@Param("id") Long id);
    // ---- 上移/下移排序 ----
    Integer selectSortById(@Param("id") Long id);
    int updateSort(@Param("id") Long id, @Param("sort") Integer sort);
    // 交换两行的主键 id（先整体加一个大步长偏移避免主键冲突，再分别归位）
    int bumpId(@Param("a") Long a, @Param("b") Long b, @Param("big") long big);
    int setId(@Param("from") long from, @Param("to") Long to);
    // ---- 排序号唯一性校验 ----
    int countBySort(@Param("sort") Integer sort);
    int countBySortExcept(@Param("id") Long id, @Param("sort") Integer sort);
}
