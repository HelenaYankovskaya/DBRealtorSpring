package com.it.app.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Request Data For converting Object class in User entity
 */
public class UserRequestDto {

    private Long id;

    @NotNull(message = "{user.name.notNull}")
    @NotEmpty(message = "{user.name.notEmpty}")
    @Size(min = 3, max = 50, message = "{user.name.size}")
    private String name;

    @NotNull(message = "{user.password.notNull}")
    @NotEmpty(message = "{user.password.notEmpty}")
    @Size(min = 3, max = 8, message = "{user.password.size}")
    private String password;

    @NotNull(message = "{user.role.notNull}")
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}



