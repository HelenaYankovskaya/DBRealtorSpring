package com.it.app.service;

import com.it.app.model.UserRole;

import java.util.List;

/**
 * Service for UserRole entity
 */
public interface UserRoleService {

    List<UserRole> findAll();

    UserRole findById(Long id);

    UserRole findByUserRole(String userRole);

    UserRole save(UserRole userRole);

    UserRole update(UserRole userRole);

    void delete(UserRole userRole);

    void deleteById(Long id);
}
