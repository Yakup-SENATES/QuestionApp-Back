package com.example.fs.request;

import lombok.Data;

@Data
public class CommentCreateRequest {

    Long id , userId, postId;
    String text;
}
