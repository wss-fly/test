package com.feitui.controller;

import com.feitui.common.R;
import com.feitui.entity.Video;
import com.feitui.service.VideoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/list")
    public R<List<Video>> listAll() {
        List<Video> list = videoService.listAll();
        return R.ok(list);
    }

    @GetMapping("/{id}")
    public R<Video> getById(@PathVariable Long id) {
        Video video = videoService.getById(id);
        if (video == null) {
            return R.fail("视频不存在");
        }
        return R.ok(video);
    }

    @PostMapping
    public R<Video> save(@RequestBody Video video) {
        Video saved = videoService.save(video);
        return R.ok(saved);
    }

    @PutMapping
    public R<Void> update(@RequestBody Video video) {
        boolean success = videoService.update(video);   
        return success ? R.ok() : R.fail("更新失败");
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        boolean success = videoService.delete(id);
        return success ? R.ok() : R.fail("删除失败");
    }
}
