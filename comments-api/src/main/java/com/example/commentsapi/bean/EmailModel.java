package com.example.commentsapi.bean;


import java.io.Serializable;

public class EmailModel implements Serializable {
    private String authorEmail;
    private String postTitle;
    private String commentText;
    private String authorUsername;
    private String CommentUsername;

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getCommentUsername() {
        return CommentUsername;
    }

    public void setCommentUsername(String commentUsername) {
        CommentUsername = commentUsername;
    }

    public EmailModel(String authorEmail, String postTitle, String commentText, String authorUsername, String commentUsername) {
        this.authorEmail = authorEmail;
        this.postTitle = postTitle;
        this.commentText = commentText;
        this.authorUsername = authorUsername;
        CommentUsername = commentUsername;
    }
}
