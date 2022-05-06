package com.example.fs.response;

import com.example.fs.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {

    Long id , userId, postId;

    public LikeResponse(Like entity){
        this.id= entity.getId();
        this.userId = entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}
