package com.it.app.service.Impl;

import com.it.app.model.User;
import com.it.app.repository.UserRepository;
import com.it.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public User getByName(String name) {
        return null;
    }

    @Override
    public User editUser(User user) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }
}
