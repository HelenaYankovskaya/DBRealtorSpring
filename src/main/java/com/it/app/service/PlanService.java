package com.it.app.service;

import com.it.app.model.Plan;

import java.util.List;

public interface PlanService {

    List<Plan> findAll();

    Plan findById(Long id);

    Plan save(Plan plan);

    Plan update(Plan plan);

    void delete(Plan plan);

    void deleteById(Long id);

}
