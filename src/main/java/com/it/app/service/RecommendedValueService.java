package com.it.app.service;

import com.it.app.model.RecommendedValue;

import java.util.List;
/**
 * Service for RV entity
 */
public interface RecommendedValueService {

    List<RecommendedValue> findAll();

    RecommendedValue findById(Long id);

    RecommendedValue save(RecommendedValue recommendedValue);

    RecommendedValue update(RecommendedValue recommendedValue);

    void delete(RecommendedValue recommendedValue);

    void deleteById(Long id);;
}
