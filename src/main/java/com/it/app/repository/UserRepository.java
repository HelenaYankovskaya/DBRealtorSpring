package com.it.app.repository;

import com.it.app.model.User;
import com.it.app.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByName(String name);

    User findByUserName(String username);

    User findUserByUserRole(UserRole userRole);
}
