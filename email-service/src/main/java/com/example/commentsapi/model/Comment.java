package com.example.commentsapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ga.emailservice.bean.CommentDeserializer;

import java.io.Serializable;

@JsonDeserialize(using = CommentDeserializer.class)
public class Comment implements Serializable {

    private static final long serialVersionUID = 4L;

    private long id;

    private String text;

    private Long postId;

    private String username;

//    private User user;
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Comment() {}

    public Comment(String text, String username) {
        this.text = text;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString(){
        return "Comment" + text;
    }
}