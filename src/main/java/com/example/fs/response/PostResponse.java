package com.example.fs.response;

import com.example.fs.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    Long id , userId;
    String userName, text, title;

    List<LikeResponse> postlikes;

    public PostResponse(Post entity, List<LikeResponse> likes) {
        this.id= entity.getId();
        this.userId= entity.getUser().getId();
        this.userName= entity.getUser().getUserName();
        this.title = entity.getTitle();
        this.text = entity.getText();
        this.postlikes = likes;
    }
}
