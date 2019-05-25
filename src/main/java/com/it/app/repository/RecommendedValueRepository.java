package com.it.app.repository;

import com.it.app.model.Plan;
import com.it.app.model.RecommendedValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendedValueRepository extends JpaRepository<RecommendedValue, Long> {

    boolean existsByRecommendedValue(Long recommendedValue);

    RecommendedValue findByRecommendedValue(Long recommendedValue);

}
