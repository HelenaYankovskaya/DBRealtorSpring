package com.it.app.repository;

import com.it.app.model.Contracts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractsRepository extends JpaRepository<Contracts, Long> {

    List<Contracts> findAllByRealtorId(Long realtorId);

}
