package com.feitui.controller;

import com.feitui.common.R;
import com.feitui.entity.Feature;
import com.feitui.service.FeatureService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/feature")
public class FeatureController {

    private final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping("/list")
    public R<List<Feature>> listAll() {
        List<Feature> list = featureService.listAll();
        return R.ok(list);
    }

    @GetMapping("/{id}")
    public R<Feature> getById(@PathVariable Long id) {
        Feature feature = featureService.getById(id);
        if (feature == null) {
            return R.fail("功能不存在");
        }
        return R.ok(feature);
    }

    @PostMapping
    public R<Feature> save(@RequestBody Feature feature) {
        Feature saved = featureService.save(feature);
        return R.ok(saved);
    }

    @PutMapping
    public R<Void> update(@RequestBody Feature feature) {
        boolean success = featureService.update(feature);
        return success ? R.ok() : R.fail("更新失败");
    }

    @DeleteMapping("/{id}")
    public R<Void> delete(@PathVariable Long id) {
        boolean success = featureService.delete(id);
        return success ? R.ok() : R.fail("删除失败");
    }
}
