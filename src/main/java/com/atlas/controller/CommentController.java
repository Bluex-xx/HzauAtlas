package com.atlas.controller;

import com.atlas.entity.Comment;
import com.atlas.entity.User;
import com.atlas.service.Impl.CommentServiceImpl;
import javafx.scene.input.DataFormat;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @ResponseBody
    @PostMapping("/report")
    public List<Comment> report(@RequestBody Comment comment){

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        System.out.println(dateStr);
        comment.setDate(dateStr);
        User user=commentService.find(comment);
        commentService.report(comment);
        return commentService.findlist(comment);
    }

    @ResponseBody
    @PostMapping("/delete")
    public int delete(@RequestBody Comment comment){
        List<String> stringList=new ArrayList<>();
        stringList.add("6");
        stringList.add("7");
        stringList.add("4");
        if(stringList.contains(comment.getUid())){
            return commentService.delete2(comment);
        }else{
            int row=commentService.delete(comment);
            System.out.println(row);
            return row;
        }
    }

}
