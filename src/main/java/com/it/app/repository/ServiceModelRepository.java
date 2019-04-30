package com.it.app.repository;

import com.it.app.model.ServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceModelRepository extends JpaRepository<ServiceModel, Long> {

    boolean existsByName(String serviceModel);

    ServiceModel findByName(String serviceModel);

}
