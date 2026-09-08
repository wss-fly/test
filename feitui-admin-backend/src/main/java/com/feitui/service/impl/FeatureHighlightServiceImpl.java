package com.feitui.service.impl;

import com.feitui.entity.FeatureHighlight;
import com.feitui.mapper.FeatureHighlightMapper;
import com.feitui.service.FeatureHighlightService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeatureHighlightServiceImpl implements FeatureHighlightService {

    private final FeatureHighlightMapper featureHighlightMapper;

    public FeatureHighlightServiceImpl(FeatureHighlightMapper featureHighlightMapper) {
        this.featureHighlightMapper = featureHighlightMapper;
    }

    @Override
    public List<FeatureHighlight> listAll() {
        return featureHighlightMapper.selectAll();
    }

    @Override
    public FeatureHighlight save(FeatureHighlight highlight) {
        featureHighlightMapper.insert(highlight);
        return highlight;
    }

    @Override
    public boolean update(FeatureHighlight highlight) {
        return featureHighlightMapper.updateById(highlight) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return featureHighlightMapper.deleteById(id) > 0;
    }
}
