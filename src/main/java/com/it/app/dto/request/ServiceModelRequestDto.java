package com.it.app.dto.request;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request Data For converting Object class in Service entity
 */
public class ServiceModelRequestDto {

    private Long id;

    @NotNull(message = "{serviceModel.serviceModel.notNull}")
    @NotEmpty(message = "{serviceModel.serviceModel.notEmpty}")
    @Size(min = 3, max = 50, message = "{serviceModel.serviceModel.size}")
    private String serviceModel;

    @NotNull(message = "{serviceModel.serviceValue.notNull}")
    @NotEmpty(message = "{serviceModel.serviceValue.notEmpty}")
    @Size(min = 3, max = 10, message = "{serviceModel.serviceValue.size}")
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
