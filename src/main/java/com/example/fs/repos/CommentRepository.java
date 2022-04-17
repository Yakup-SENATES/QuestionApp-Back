package com.example.fs.repos;

import com.example.fs.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {


    List<Comments> findByUserIdAndPostId(Long userId , Long postId);

    List<Comments> findByUserId(Long userId);

    List<Comments> findByPostId(Long postId);
}
