package com.feitui.controller;

import com.feitui.common.R;
import com.feitui.entity.FeatureHighlight;
import com.feitui.service.FeatureHighlightService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/highlight")
public class FeatureHighlightController {

    private final FeatureHighlightService featureHighlightService;

    public FeatureHighlightController(FeatureHighlightService featureHighlightService) {
        this.featureHighlightService = featureHighlightService;
    }

    @GetMapping("/list")
    public R<List<FeatureHighlight>> listAll() {
        List<FeatureHighlight> list = featureHighlightService.listAll();
        return R.ok(list);
    }

    @PostMapping
    public R<FeatureHighlight> save(@RequestBody FeatureHighlight highlight) {
        FeatureHighlight saved = featureHighlightService.save(highlight);
        return R.ok(saved);
    }

    @PutMapping
    public R<Void> update(@RequestBody FeatureHighlight highlight) {
        boolean success = featureHighlightService.update(highlight);
        return success ? R.ok() : R.fail("更新失败");
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        boolean success = featureHighlightService.delete(id);
        return success ? R.ok() : R.fail("删除失败");
    }
}
