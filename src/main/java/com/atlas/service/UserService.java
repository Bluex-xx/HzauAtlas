package com.atlas.service;

import com.atlas.entity.User;

public interface UserService {
    int insert(User user);
    void update(User user);
    User find(User user);
}
