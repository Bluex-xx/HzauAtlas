package com.atlas.service.Impl;

import com.atlas.dao.CommentDAO;
import com.atlas.entity.Comment;
import com.atlas.entity.User;
import com.atlas.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDAO commentDAO;

    @Override
    public void report(Comment comment) {
        commentDAO.report(comment);
    }

    @Override
    public User find(Comment comment) {
        return commentDAO.find(comment);
    }

    @Override
    public List<Comment> findlist(Comment comment) {
        return commentDAO.findlist(comment);
    }
}
