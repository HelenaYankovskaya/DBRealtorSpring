package com.it.app.service;

import com.it.app.model.Plan;

import java.util.List;

public interface PlanService {

    Plan addPlan(Plan plan);
    void delete(long id);
    Plan getByName(String name);
    Plan editPlan(Plan plan);
    List<Plan> getAll();

}
