package com.example.fs.repos;

import com.example.fs.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {


    List<Comments> findByUserIdAndPostId(Long userId , Long postId);

    List<Comments> findByUserId(Long userId);

    List<Comments> findByPostId(Long postId);

    @Query(value = "select * from comment where post_id in :postIds limit 5", nativeQuery = true)
    List<Comments> findUserCommentsByPostId(@Param("postIds") List<Long> postIds);
}
