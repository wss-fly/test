package com.feitui.service.impl;

import com.feitui.entity.Feature;
import com.feitui.mapper.FeatureMapper;
import com.feitui.service.FeatureService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeatureServiceImpl implements FeatureService {

    private final FeatureMapper featureMapper;

    public FeatureServiceImpl(FeatureMapper featureMapper) {
        this.featureMapper = featureMapper;
    }

    @Override
    public List<Feature> listAll() {
        return featureMapper.selectAll();
    }

    @Override
    public Feature getById(Long id) {
        return featureMapper.selectById(id);
    }

    @Override
    public Feature save(Feature feature) {
        featureMapper.insert(feature);
        return feature;
    }

    @Override
    public boolean update(Feature feature) {
        return featureMapper.updateById(feature) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return featureMapper.deleteById(id) > 0;
    }
}
