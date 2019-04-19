package com.it.app.service;

import com.it.app.model.ServiceModel;

import java.util.List;

public interface ServiceModelService {

    ServiceModel addServiceModel(ServiceModel serviceModel);

    void delete(long id);

    ServiceModel getByName(String name);

    ServiceModel editServiceModel(ServiceModel serviceModel);

    List<ServiceModel> getAll();


}
