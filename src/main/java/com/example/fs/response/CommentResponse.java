package com.example.fs.response;

import com.example.fs.entities.Comments;
import lombok.Data;

@Data
public class CommentResponse {
    Long id, userId  ;
    String text,userName;

    public CommentResponse(Comments entity){
        this.id = entity.getId();
        this.text= entity.getText();
        this.userId=entity.getUser().getId();
        this.userName=entity.getUser().getUserName();
    }

}
