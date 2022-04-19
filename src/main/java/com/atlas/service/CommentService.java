package com.atlas.service;

import com.atlas.entity.Comment;
import com.atlas.entity.User;

import java.util.List;

public interface CommentService {
    void report(Comment comment);
    User find(Comment comment);
    List<Comment> findlist(Comment comment);
    int delete(Comment comment);
    int delete2(Comment comment);
}
