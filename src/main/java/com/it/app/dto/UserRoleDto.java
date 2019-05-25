package com.it.app.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object class for UserRole entity
 */
public class UserRoleDto {

    private Long id;

    @NotNull(message = "{role.name.notNull}")
    @NotEmpty(message = "{role.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{role.name.size}")
    private String userRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
