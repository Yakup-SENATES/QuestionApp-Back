package com.example.fs.controllers;

import com.example.fs.entities.Like;
import com.example.fs.request.LikeCreateRequest;
import com.example.fs.response.LikeResponse;
import com.example.fs.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }


    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return likeService.getAllLikes(userId,postId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateRequest request){
        return likeService.createLike(request);
    }

    @GetMapping("{likeId}")
    public Like getLike(@PathVariable Long likeId){
        return likeService.getLike(likeId);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId){
        likeService.deleteLike(likeId);
    }

}
