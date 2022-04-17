package com.example.fs.controllers;

import com.example.fs.entities.Comments;
import com.example.fs.request.CommentCreateRequest;
import com.example.fs.request.CommentUpdateRequest;
import com.example.fs.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comments> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getAllComments(userId,postId);
    }

    @GetMapping("/{commentId}")
    public Comments getComment(@PathVariable Long commentId){
        return commentService.getComment(commentId);
    }

    @PostMapping
    public Comments createComment(@RequestBody CommentCreateRequest request){

        return commentService.createComment(request);
    }

    @PutMapping(path = {"{commentId}"})
    public Comments updateComment(@PathVariable Long commentId,@RequestBody CommentUpdateRequest updateComment){
        return commentService.updateComment(commentId, updateComment);
    }

    @DeleteMapping("{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }

}
