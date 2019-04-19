package com.it.app.service.Impl;

import com.it.app.model.Plan;
import com.it.app.repository.PlanRepository;
import com.it.app.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    PlanRepository planRepository;

    @Override
    public Plan addPlan(Plan plan) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public Plan getByName(String name) {
        return null;
    }

    @Override
    public Plan editPlan(Plan plan) {
        return null;
    }

    @Override
    public List<Plan> getAll() {
        return null;
    }
}
