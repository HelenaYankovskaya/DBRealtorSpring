package com.it.app.service.Impl;

import com.it.app.model.RecommendedValue;
import com.it.app.repository.RecommendedValueRepository;
import com.it.app.service.RecommendedValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendedValueServiceImpl implements RecommendedValueService {
    @Autowired
    RecommendedValueRepository recommendedValueRepository;

    @Override
    public RecommendedValue addRecommendedValue(RecommendedValue recommendedValue) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public RecommendedValue getByName(String name) {
        return null;
    }

    @Override
    public RecommendedValue editRecommendedValue(RecommendedValue recommendedValue) {
        return null;
    }

    @Override
    public List<RecommendedValue> getAll() {
        return null;
    }
}
