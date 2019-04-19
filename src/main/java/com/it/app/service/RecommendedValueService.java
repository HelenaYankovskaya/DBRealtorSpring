package com.it.app.service;

import com.it.app.model.RecommendedValue;

import java.util.List;

public interface RecommendedValueService {

    RecommendedValue addRecommendedValue(RecommendedValue recommendedValue);

    void delete(long id);

    RecommendedValue getByName(String name);

    RecommendedValue editRecommendedValue(RecommendedValue recommendedValue);

    List<RecommendedValue> getAll();
}
