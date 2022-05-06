package com.example.fs.services;

import com.example.fs.entities.Like;
import com.example.fs.entities.Post;
import com.example.fs.entities.User;
import com.example.fs.repos.PostRepository;
import com.example.fs.request.PostCreateRequest;
import com.example.fs.request.PostUpdateRequest;
import com.example.fs.response.LikeResponse;
import com.example.fs.response.PostResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;
    private UserService userService;
    private LikeService likeService;
    /*
    * Constructor
    */
    public PostService(@Lazy PostRepository postRepository,@Lazy UserService userService,@Lazy LikeService likeService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeService = likeService;
    }

    public void setLikeService(LikeService likeService){
        this.likeService = likeService;
    }

    /*
    * Get All Posts
    */
    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()) {
            list = postRepository.findByUserId(userId.get());
        }else {
            list= postRepository.findAll();
        }
        return list.stream().map(post -> {
          List<LikeResponse> likes= likeService.getAllLikes(Optional.ofNullable(null),Optional.of(post.getId()));
            return  new PostResponse(post,likes);
        }).collect(Collectors.toList());

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



