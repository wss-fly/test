package com.feitui.service.impl;

import com.feitui.entity.Video;
import com.feitui.mapper.VideoMapper;
import com.feitui.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoMapper videoMapper;

    public VideoServiceImpl(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
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
        int n = videoMapper.deleteById(id);
        if (n > 0) {
            // 删除后紧凑重排：后面所有记录 id 前移，保证 id 连续无空洞
            videoMapper.shiftIdDown(id);
        }
        return n > 0;
    }
}
