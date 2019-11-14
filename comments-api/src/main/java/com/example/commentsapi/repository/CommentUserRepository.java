package com.example.commentsapi.repository;

import com.example.commentsapi.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentUserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Comment> getCommentByUserId(Long id) {
        List<Comment> comments = new ArrayList<>();
        String sql = "select * from comments join users on user.id = comments.user_id where user_id = ?";
        jdbcTemplate.query(sql, new Object[]{id},
                (rs, num) -> comments.add(
                        new Comment(
                                rs.getLong("id"),
                                rs.getString("text"),
                                rs.getLong("post_id"),
                                rs.getLong("user_id"))));
        return comments;
    }

}
