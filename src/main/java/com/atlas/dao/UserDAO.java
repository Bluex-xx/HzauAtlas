package com.atlas.dao;

import com.atlas.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    void insert(User user);
    void update(User user);
    User find(User user);
}
