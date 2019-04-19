package com.it.app.service.Impl;

import com.it.app.model.ServiceModel;
import com.it.app.repository.ServiceModelRepository;
import com.it.app.service.ServiceModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceModelServiceImpl implements ServiceModelService {
    @Autowired
    ServiceModelRepository serviceModelRepository;

    @Override
    public ServiceModel addServiceModel(ServiceModel serviceModel) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public ServiceModel getByName(String name) {
        return null;
    }

    @Override
    public ServiceModel editServiceModel(ServiceModel serviceModel) {
        return null;
    }

    @Override
    public List<ServiceModel> getAll() {
        return null;
    }
}
