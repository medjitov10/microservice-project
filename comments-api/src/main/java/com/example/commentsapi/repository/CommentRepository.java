package com.example.commentsapi.repository;

import com.example.commentsapi.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByUsername(String username);
    List<Comment> findByPostId(Long postId);

//    @Query("FROM comments WHERE postId = ?0")
//    List<Comment> getByPostId(Long postId);
}
