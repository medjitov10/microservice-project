package com.ga.emailservice.bean;

public class EmailModel {
    private String authorEmail;
    private String postTitle;
    private String commentText;
    private String authorUsername;
    private String CommentUsername;

    public String getCommentUsername() {
        return CommentUsername;
    }

    public void setCommentUsername(String commentUsername) {
        CommentUsername = commentUsername;
    }

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


    public EmailModel() {
    }

}
