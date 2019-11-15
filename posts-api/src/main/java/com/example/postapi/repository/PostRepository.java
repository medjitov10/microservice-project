package com.example.postapi.repository;

import com.example.postapi.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    public List<Post> findByUsername(String username);
}
