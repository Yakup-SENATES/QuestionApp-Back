package com.example.fs.repos;

import com.example.fs.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post , Long> {
    List<Post> findByUserId(Long aLong);
}
