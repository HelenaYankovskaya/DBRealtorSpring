package com.it.app.repository;

import com.it.app.model.Plan;
import com.it.app.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair, Long> {

    boolean existsByName(String repair);

    Repair findByName(String repair);

}
