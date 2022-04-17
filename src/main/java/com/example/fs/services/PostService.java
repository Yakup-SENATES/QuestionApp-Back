package com.example.fs.services;

import com.example.fs.entities.Post;
import com.example.fs.entities.User;
import com.example.fs.repos.PostRepository;
import com.example.fs.request.PostCreateRequest;
import com.example.fs.request.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;

    /*
    * Constructor
    */
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;

    }


    /*
    * Get All Posts
    */
    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent())
            return postRepository.findByUserId(userId.get());
        return
                postRepository.findAll();
    }


    /*
    * Get Post By ID
    */
    public Post getPostById(Long userId) {
        return postRepository.findById(userId).orElse(null);
    }

    /*
    * Create Post
    */
    public Post createPost(PostCreateRequest newPostRequest) {
        User user = userService.getUserById(newPostRequest.getUserId());
        if (user==null) return null;
        Post post = new Post();
        post.setId(newPostRequest.getId());
        post.setText(newPostRequest.getText());
        post.setTitle(newPostRequest.getTitle());
        post.setUser(user);
        return postRepository.save(post);
    }


    /*
    * Update Post
    */
    public Post updatePost(Long postId, PostUpdateRequest newPost) {
        Optional<Post> post =postRepository.findById(postId);

        if (post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(newPost.getText());
            toUpdate.setTitle(newPost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }

        return null;
    }
    /*
     * Delete post
     */

    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

}



