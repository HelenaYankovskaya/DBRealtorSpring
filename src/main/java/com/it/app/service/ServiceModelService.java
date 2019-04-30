package com.it.app.service;

import com.it.app.model.ServiceModel;

import java.util.List;

public interface ServiceModelService {

    List<ServiceModel> findAll();

    ServiceModel findById(Long id);

    ServiceModel save(ServiceModel serviceModel);

    ServiceModel update(ServiceModel serviceModel);

    void delete(ServiceModel serviceModel);

    void deleteById(Long id);

}
