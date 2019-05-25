package com.it.app.dto.response;

import com.it.app.dto.UserRoleDto;

/**
 * Response Data Transfer Object class to user entity
 */
public class UserResponseDto {

    private Long id;

    private String name;

    private String password;

    private UserRoleDto userRole;

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

    public UserRoleDto getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleDto userRole) {
        this.userRole = userRole;
    }

}
