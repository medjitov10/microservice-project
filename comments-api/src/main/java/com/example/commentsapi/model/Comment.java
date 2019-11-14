package com.example.commentsapi.model;


import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String text;

    @Column
    private Long post_id;

    public Comment(Long id, String text, Long post_id, Long user_id) {
        this.id = id;
        this.text = text;
        this.post_id = post_id;
        this.user_id = user_id;
    }

    @Column
    private Long user_id;

    public Comment() {}

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

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}