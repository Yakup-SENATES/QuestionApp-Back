package com.example.fs.services;

import com.example.fs.entities.Like;
import com.example.fs.entities.Post;
import com.example.fs.entities.User;
import com.example.fs.repos.LikeRepository;
import com.example.fs.request.LikeCreateRequest;
import com.example.fs.response.LikeResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private UserService userService;
    private LikeRepository likeRepository;
    private PostService postService;

    public LikeService(@Lazy LikeRepository likeRepository,@Lazy UserService userService,@Lazy PostService postService) {
        this.userService = userService;
        this.likeRepository = likeRepository;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like>list;
            if (userId.isPresent() && postId.isPresent() ){
                list =likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
            }else if (userId.isPresent()){
                list= likeRepository.findByUserId(userId);
            }else if (postId.isPresent()){
                list=  likeRepository.findByPostId(postId);
            }else
                list = likeRepository.findAll();
                return list.stream().map(LikeResponse::new).collect(Collectors.toList());

    }

    public Like getLike(Long likeId) {
         return likeRepository.findById(likeId).orElse(null);
    }

    public Like createLike(LikeCreateRequest request) {
        User user = userService.getUserById(request.getUserId());
        Post post= postService.getPostById(request.getPostId());

        if (user!=null && post!=null){
            Like like = new Like();
            like.setId(request.getId());
            like.setPost(post);
            like.setUser(user);
            return likeRepository.save(like);
        }
        return null;

    }

    public void deleteLike(Long likeId) {
        Optional<Like> like= likeRepository.findById(likeId);
        if (like.isPresent())
            likeRepository.deleteById(likeId);
    }
}
