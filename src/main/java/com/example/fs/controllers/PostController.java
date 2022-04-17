package com.example.fs.controllers;

import com.example.fs.entities.Post;
import com.example.fs.request.PostCreateRequest;
import com.example.fs.request.PostUpdateRequest;
import com.example.fs.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }
    /*
    *RequestParam gelen request deki değerleri parse ederek alır
    * PathVariable ise direk parametreyi olduğu gibi alır
    * ~~postId de olduğu gibi
    */

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createPost(newPostRequest);
    }


    @GetMapping(path = "/{postId}")
    public Post getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PutMapping(path ="{postId}" )
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost){
        return postService.updatePost(postId, updatePost);
    }


    @DeleteMapping(path = "/{postId}")
    public void deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
    }




}
