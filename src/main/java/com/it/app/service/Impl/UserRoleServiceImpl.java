package com.it.app.service.Impl;

import com.it.app.model.UserRole;
import com.it.app.repository.UserRoleRepository;
import com.it.app.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRole addUserRole(UserRole userRole) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public UserRole getByName(String name) {
        return null;
    }

    @Override
    public UserRole editUserRole(UserRole userRole) {
        return null;
    }

    @Override
    public List<UserRole> getAll() {
        return null;
    }
}
