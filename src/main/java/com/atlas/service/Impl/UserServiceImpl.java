package com.atlas.service.Impl;

import com.atlas.dao.UserDAO;
import com.atlas.entity.User;
import com.atlas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Override
    public void insert(User user) {
        userDAO.insert(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public User find(User user) {
        return userDAO.find(user);
    }
}
