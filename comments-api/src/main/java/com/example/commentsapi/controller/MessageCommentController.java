package com.example.commentsapi.controller;

import com.example.commentsapi.model.Comment;
import com.example.commentsapi.mq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageCommentController {

    @Autowired
    Sender sender;

//    @GetMapping("/send/{msg}")
//    public String send(@PathVariable String msg) throws Exception{
//        Comment comment = new Comment();
//        comment.setPostId(1L);
//        comment.setUsername("qweqwe");
//        comment.setId(1L);
//        comment.setText(msg);
//        sender.send(comment);
//        return "message sent";
//    }
}
