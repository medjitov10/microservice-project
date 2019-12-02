package com.example.commentsapi.bean;


public class EmailModel {
    private String authorEmail;
    private String postTitle;
    private String commentText;
    private String authorUsername;
    private String CommentUsername;

    public EmailModel(String authorEmail, String postTitle, String commentText, String authorUsername, String commentUsername) {
        this.authorEmail = authorEmail;
        this.postTitle = postTitle;
        this.commentText = commentText;
        this.authorUsername = authorUsername;
        CommentUsername = commentUsername;
    }
}
