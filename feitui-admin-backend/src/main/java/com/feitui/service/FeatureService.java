package com.feitui.service;

import com.feitui.entity.Feature;
import java.util.List;

public interface FeatureService {
    List<Feature> listAll();
    Feature getById(Long id);
    Feature save(Feature feature);
    boolean update(Feature feature);
    boolean delete(Long id);
}
