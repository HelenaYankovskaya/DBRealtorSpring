package com.it.app.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object class for Plan entity
 */
public class PlanDto {

    private Long id;

    @NotNull(message = "{plan.plan.notNull}")
    @NotEmpty(message = "{plan.plan.notEmpty}")
    @Size(min = 3, max = 50, message = "{plan.plan.size}")
    private String plan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}
