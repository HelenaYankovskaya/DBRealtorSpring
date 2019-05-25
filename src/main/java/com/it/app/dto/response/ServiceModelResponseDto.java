package com.it.app.dto.response;

/**
 * Response Data Transfer Object class to ServiceModel entity
 */
public class ServiceModelResponseDto {

    private Long id;

    private String serviceModel;

    private Long serviceValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceModel() {
        return serviceModel;
    }

    public void setServiceModel(String serviceModel) {
        this.serviceModel = serviceModel;
    }

    public Long getServiceValue() {
        return serviceValue;
    }

    public void setServiceValue(Long serviceValue) {
        this.serviceValue = serviceValue;
    }

}
