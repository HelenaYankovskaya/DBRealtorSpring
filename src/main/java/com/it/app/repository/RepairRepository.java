package com.it.app.repository;

import com.it.app.model.Plan;
import com.it.app.model.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair, Long> {

    boolean existsByRepair(String repair);

    Repair findByRepair(String repair);

}
