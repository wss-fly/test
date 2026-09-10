package com.feitui.admin.controller;

import com.feitui.admin.common.R;
import com.feitui.entity.Video;
import com.feitui.service.VideoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/video")
public class VideoAdminController {

    private final VideoService videoService;

    public VideoAdminController(VideoService videoService) {
        this.videoService = videoService;
    }

    // 后台视频管理分页列表（含禁用项）
    @GetMapping("/page")
    public R<PageInfo<Video>> page(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        return R.ok(videoService.pageAdmin(page, Math.min(size, 100)));
    }

    // 新增视频
    @PostMapping
    public R<Void> create(@RequestBody Video video) {
        if (video.getVideoUrl() == null || video.getVideoUrl().isBlank()) {
            return R.fail("视频地址不能为空");
        }
        if (video.getSort() == null) video.setSort(0);
        if (videoService.existSort(video.getSort(), null)) {
            return R.fail("排序号 " + video.getSort() + " 已存在，请更换");
        }
        if (video.getStatus() == null) video.setStatus(1);
        videoService.save(video);
        return R.ok();
    }

    // 修改视频
    @PutMapping("/{id}")
    public R<Void> update(@PathVariable Long id, @RequestBody Video video) {
        if (video.getVideoUrl() == null || video.getVideoUrl().isBlank()) {
            return R.fail("视频地址不能为空");
        }
        if (video.getSort() != null && videoService.existSort(video.getSort(), id)) {
            return R.fail("排序号 " + video.getSort() + " 已存在，请更换");
        }
        video.setId(id);
        videoService.update(video);
        return R.ok();
    }

    // 删除视频
    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        videoService.delete(id);
        return R.ok();
    }

    // 上移/下移：交换两行视频的顺序与主键，保证 ID 连续无空洞
    @PostMapping("/swap")
    public R<Void> swap(@RequestParam Long idA, @RequestParam Long idB) {
        videoService.reorderByIds(idA, idB);
        return R.ok();
    }
}