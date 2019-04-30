package com.it.app.service.Impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.ServiceModel;
import com.it.app.repository.ServiceModelRepository;
import com.it.app.service.ServiceModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ServiceModelServiceImpl implements ServiceModelService {

    private final LocalizedMessageSource localizedMessageSource;

    private final ServiceModelRepository serviceModelRepository;

    public ServiceModelServiceImpl(LocalizedMessageSource localizedMessageSource, ServiceModelRepository serviceModelRepository) {
        this.localizedMessageSource = localizedMessageSource;
        this.serviceModelRepository = serviceModelRepository;
    }

    @Override
    public List<ServiceModel> findAll() {
        return serviceModelRepository.findAll();
    }

    @Override
    public ServiceModel findById(Long id) {
        return serviceModelRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.serviceModel.notExist", new Object[]{})));
    }

    public ServiceModel save(ServiceModel serviceModel) {
        validate(serviceModel.getId() != null, localizedMessageSource.getMessage("error.serviceModel.notHaveId", new Object[]{}));
        validate(serviceModelRepository.existsByName(serviceModel.getServiceModel()), localizedMessageSource.getMessage("error.serviceModel.name.notUnique", new Object[]{}));
        return saveAndFlush(serviceModel);
    }

    @Override
    public ServiceModel update(ServiceModel serviceModel) {
        final Long id = serviceModel.getId();
        validate(id == null, localizedMessageSource.getMessage("error.serviceModel.haveId", new Object[]{}));
        final ServiceModel duplicateServiceModel = serviceModelRepository.findByName(serviceModel.getServiceModel());
        final boolean isDuplicateExists = duplicateServiceModel != null && !Objects.equals(duplicateServiceModel.getId(), id);
        validate(isDuplicateExists, localizedMessageSource.getMessage("error.serviceModel.name.notUnique", new Object[]{}));
        return saveAndFlush(serviceModel);
    }

    @Override
    public void delete(ServiceModel serviceModel) {
        validate(serviceModel.getId() == null, localizedMessageSource.getMessage("error.serviceModel.haveId", new Object[]{}));
        serviceModelRepository.delete(serviceModel);
    }

    @Override
    public void deleteById(Long id) {
        serviceModelRepository.deleteById(id);
    }

    private ServiceModel saveAndFlush(ServiceModel serviceModel) {
        //   validate(serviceModel.getPassport() == null || serviceModel.getServices() == null, localizedMessageSource.getMessage("error.serviceModel.passport.isNull", new Object[]{}));
        // serviceModel.setPassport(serviceModelService.      (serviceModel.getUserRole().getId()));
        return serviceModelRepository.saveAndFlush(serviceModel);
    }

    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
