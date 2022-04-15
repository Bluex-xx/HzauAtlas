package com.atlas.dao;

import com.atlas.entity.Comment;
import com.atlas.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDAO {
    void report(Comment comment);
    User find(Comment comment);
    List<Comment> findlist(Comment comment);
}
