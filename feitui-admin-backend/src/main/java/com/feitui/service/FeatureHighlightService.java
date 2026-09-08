package com.feitui.service;

import com.feitui.entity.FeatureHighlight;
import java.util.List;

public interface FeatureHighlightService {
    List<FeatureHighlight> listAll();
    FeatureHighlight save(FeatureHighlight highlight);
    boolean update(FeatureHighlight highlight);
    boolean delete(Long id);
}
