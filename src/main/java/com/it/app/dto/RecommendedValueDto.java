package com.it.app.dto;

/**
 * Data Transfer Object class for RecommendedValue entity
 */
public class RecommendedValueDto {

    private Long id;

    private Long recommendedValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecommendedValue() {
        return recommendedValue;
    }

    public void setRecommendedValue(Long recommendedValue) {
        this.recommendedValue = recommendedValue;
    }
}
