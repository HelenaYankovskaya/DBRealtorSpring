package com.it.app.repository;

import com.it.app.model.Contracts;
import com.it.app.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractsRepository extends JpaRepository<Contracts, Long> {

    boolean existsByData(String data);

    Contracts findByData(String data);

}
