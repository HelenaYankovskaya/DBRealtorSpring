package com.it.app.repository;

import com.it.app.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    boolean existsByUserRole(String userRole);

    UserRole findByUserRole(String userRole);

}
