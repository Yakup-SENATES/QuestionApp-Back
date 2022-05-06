package com.example.fs.services;

import com.example.fs.entities.Comments;
import com.example.fs.entities.Post;
import com.example.fs.entities.User;
import com.example.fs.repos.CommentRepository;
import com.example.fs.request.CommentCreateRequest;
import com.example.fs.request.CommentUpdateRequest;
import com.example.fs.response.CommentResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentRepository commentRepository ;
    private UserService userService;
    private PostService postService;

    /*
    * Constructor
    */
    public CommentService(@Lazy CommentRepository commentRepository,@Lazy UserService userService,@Lazy PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    /*
    * get ALl Comments
    * */
    public List<CommentResponse> getAllComments(Optional<Long> userId , Optional<Long> postId) {
        List<Comments> comments;
        if (userId.isPresent() && postId.isPresent()){
            comments= commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }else if (userId.isPresent()){
            comments= commentRepository.findByUserId(userId.get());
        }else if (postId.isPresent()){
            comments= commentRepository.findByPostId(postId.get());
        }else
            comments= commentRepository.findAll();
        return comments.stream().map(comment-> new CommentResponse(comment)).collect(Collectors.toList());    }

    public Comments getComment(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comments createComment(CommentCreateRequest request) {
        User user = userService.getUserById(request.getUserId());
        Post post = postService.getPostById(request.getPostId());
        if (user!=null && post!=null ){
            Comments newComment = new Comments();
            newComment.setId(request.getId());
            newComment.setText(request.getText());
            newComment.setPost(post);
            newComment.setUser(user);
            return commentRepository.save(newComment);
        }
        return null;
    }

    public Comments updateComment(Long commentId, CommentUpdateRequest updateComment) {
       Optional<Comments> comment = commentRepository.findById(commentId);
       if (comment.isPresent()){
           Comments newComment = comment.get();
           newComment.setText(updateComment.getText());
           return commentRepository.save(newComment);
       }

        return null;
    }

    public void deleteComment(Long commentId) {
       Optional<Comments> comment=  commentRepository.findById(commentId);

       if (comment.isPresent()) commentRepository.deleteById(commentId);
    }
}
