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
}
