package com.it.app.service;

import com.it.app.model.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole addUserRole(UserRole userRole);
    void delete(long id);
    UserRole getByName(String name);
    UserRole editUserRole(UserRole userRole);
    List<UserRole> getAll();

}
