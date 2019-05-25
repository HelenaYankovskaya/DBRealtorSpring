package com.it.app.repository;

import com.it.app.model.Plan;
import com.it.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    boolean existsByPlan(String plan);

    Plan findByPlan(String plan);

}
