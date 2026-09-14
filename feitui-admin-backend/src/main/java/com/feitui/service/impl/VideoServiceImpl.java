package com.feitui.service.impl;

import com.feitui.admin.service.OssService;
import com.feitui.entity.Video;
import com.feitui.mapper.VideoMapper;
import com.feitui.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoMapper videoMapper;
    private final OssService ossService;
    private static final Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);

    public VideoServiceImpl(VideoMapper videoMapper, OssService ossService) {
        this.videoMapper = videoMapper;
        this.ossService = ossService;
    }

    @Override
    public List<Video> listAll() {
        return videoMapper.selectAll();
    }

    @Override
    public PageInfo<Video> pageAdmin(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(videoMapper.selectAllForAdmin());
    }

    @Override
    public Video getById(Long id) {
        return videoMapper.selectById(id);
    }

    @Override
    @Transactional
    public Video save(Video video) {
        // 复用最小空闲 id：删掉中间记录后的空号会被新记录顶上，保证 id 从 1 连续、按顺序递增
        for (int attempt = 0; attempt < 5; attempt++) {
            long nextId = 1;
            Set<Long> used = new HashSet<>();
            for (Integer id : videoMapper.selectIds()) used.add(id.longValue());
            while (used.contains(nextId)) nextId++;
            video.setId(nextId);
            try {
                videoMapper.insert(video);
                return video;
            } catch (DuplicateKeyException e) {
                // 并发下两人同时抢占同一空号，重试重新计算
            }
        }
        throw new RuntimeException("创建失败，请重试");
    }

    @Override
    public boolean update(Video video) {
        return videoMapper.updateById(video) > 0;
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Video v = videoMapper.selectById(id);
        int n = videoMapper.deleteById(id);
        if (n > 0) {
            // 删除后紧凑重排：后面所有记录 id 前移，保证 id 连续无空洞
            videoMapper.shiftIdDown(id);
            // 同步删除 OSS 上的视频/封面文件（仅删属于本 bucket 的对象，外部链接忽略）。
            // OSS 删除失败不阻断 DB 删除，避免记录删掉却留文件导致前台空壳。
            if (v != null) {
                safeDeleteOss(v.getVideoUrl());
                safeDeleteOss(v.getCoverImage());
            }
        }
        return n > 0;
    }

    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        if (ids == null || ids.isEmpty()) return;
        // 按 id 降序逐个删除（复用单条删除逻辑：含 OSS 清理与 ID 紧凑重排）。
        // 降序删除使 shiftIdDown 只会前移已删除行之后的记录，不会影响后续待删行。
        List<Long> sorted = ids.stream().distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (Long id : sorted) {
            delete(id);
        }
    }

    private void safeDeleteOss(String url) {
        if (url == null || url.isBlank()) return;
        try {
            ossService.deleteObjectByUrl(url);
        } catch (Exception e) {
            // 记录日志便于排查，但不阻断记录删除
            log.warn("同步删除 OSS 文件失败（请手动清理）：{} - {}", url, e.getMessage());
        }
    }

    @Override
    @Transactional
    public void reorderByIds(Long idA, Long idB) {
        Integer sortA = videoMapper.selectSortById(idA);
        Integer sortB = videoMapper.selectSortById(idB);
        if (sortA == null || sortB == null) {
            throw new RuntimeException("欲调整的视频不存在");
        }
        // 1) 交换排序号，展示顺序随之变化
        videoMapper.updateSort(idA, sortB);
        videoMapper.updateSort(idB, sortA);
        // 2) 交换主键 id，使 ID 列跟随显示顺序保持连续
        long BIG = 1_000_000_000L;
        videoMapper.bumpId(idA, idB, BIG);
        videoMapper.setId(idA.longValue() + BIG, idB);
        videoMapper.setId(idB.longValue() + BIG, idA);
    }

    @Override
    public boolean existSort(Integer sort, Long editId) {
        if (sort == null) return false;
        return editId == null
                ? videoMapper.countBySort(sort) > 0
                : videoMapper.countBySortExcept(editId, sort) > 0;
    }
}
