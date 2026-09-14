package com.feitui.service;

import com.feitui.entity.Video;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface VideoService {
    List<Video> listAll();
    PageInfo<Video> pageAdmin(int page, int size);
    Video getById(Long id);
    Video save(Video video);
    boolean update(Video video);
    boolean delete(Long id);
    // 批量删除视频（含 OSS 文件清理与 ID 紧凑重排）
    void deleteBatch(List<Long> ids);
    // 交换两行的顺序与主键，使 ID 跟随显示顺序保持连续
    void reorderByIds(Long idA, Long idB);
    // 排序号是否存在（editId 非空时排除该记录自身）
    boolean existSort(Integer sort, Long editId);
}
