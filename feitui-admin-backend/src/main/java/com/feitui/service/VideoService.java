package com.feitui.service;

import com.feitui.entity.Video;
import java.util.List;

public interface VideoService {
    List<Video> listAll();
    Video getById(Long id);
    Video save(Video video);
    boolean update(Video video);
    boolean delete(Long id);
}
