package com.it.app.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object class for Repair entity
 */
public class RepairDto {

    private Long id;

    @NotNull(message = "{repair.repair.notNull}")
    @NotEmpty(message = "{repair.repair.notEmpty}")
    @Size(min = 3, max = 50, message = "{repair.repair.size}")
    private String repair;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepair() {
        return repair;
    }

    public void setRepair(String repair) {
        this.repair = repair;
    }
}
