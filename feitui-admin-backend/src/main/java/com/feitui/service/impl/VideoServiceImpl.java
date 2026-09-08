package com.feitui.service.impl;

import com.feitui.entity.Video;
import com.feitui.mapper.VideoMapper;
import com.feitui.service.VideoService;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public Video getById(Long id) {
        return videoMapper.selectById(id);
    }

    @Override
    public Video save(Video video) {
        videoMapper.insert(video);
        return video;
    }

    @Override
    public boolean update(Video video) {
        return videoMapper.updateById(video) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return videoMapper.deleteById(id) > 0;
    }
}
