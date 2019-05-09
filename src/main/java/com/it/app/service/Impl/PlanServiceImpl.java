package com.it.app.service.Impl;

import com.it.app.component.LocalizedMessageSource;
import com.it.app.model.Plan;
import com.it.app.repository.PlanRepository;
import com.it.app.service.PlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {

    private final LocalizedMessageSource localizedMessageSource;

    private final PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository, LocalizedMessageSource localizedMessageSource) {
        this.planRepository = planRepository;
        this.localizedMessageSource = localizedMessageSource;
    }

    @Override
    public List<Plan> findAll() {
        return planRepository.findAll();
    }

    @Override
    public Plan findById(Long id) {
        return planRepository.findById(id).orElseThrow(() -> new RuntimeException(localizedMessageSource.getMessage("error.plan.notExist", new Object[]{})));
    }

    @Override
    public Plan save(Plan plan) {
        validate(plan.getId() != null, localizedMessageSource.getMessage("error.plan.notHaveId", new Object[]{}));
        validate(planRepository.existsByPlan(plan.getPlan()), localizedMessageSource.getMessage("error.plan.name.notUnique", new Object[]{}));
        return planRepository.saveAndFlush(plan);
    }

    @Override
    public Plan update(Plan plan) {
        final Long planId = plan.getId();
        validate(planId == null, localizedMessageSource.getMessage("error.plan.haveId", new Object[]{}));
        final Plan duplicatePlan = planRepository.findByPlan(plan.getPlan());
        final boolean isDuplicateExists = duplicatePlan!= null && !Objects.equals(duplicatePlan.getId(), planId);
        validate(isDuplicateExists, localizedMessageSource.getMessage("error.plan.name.notUnique", new Object[]{}));
        return planRepository.saveAndFlush(plan);
    }

    @Override
    public void delete(Plan plan) {
        validate(plan.getId() == null, localizedMessageSource.getMessage("error.plan.haveId", new Object[]{}));
        planRepository.delete(plan);
    }

    @Override
    public void deleteById(Long id) {
        planRepository.deleteById(id);
    }


    private void validate(boolean expression, String errorMessage) {
        if (expression) {
            throw new RuntimeException(errorMessage);
        }
    }
}
