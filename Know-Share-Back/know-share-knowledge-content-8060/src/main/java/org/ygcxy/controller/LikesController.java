package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.comment.Application;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.dto.request.LikesDtoRequest;
import org.ygcxy.service.LikeService;

@RestController
@RequestMapping(Application.API_VERSION + "/likes")
@RequiredArgsConstructor
public class LikesController {

    private final LikeService likeService;

    @PostMapping("/")
    public ResponseResult likesKnow(
            @RequestBody LikesDtoRequest request
    ){
        return likeService.likeKnow(request);
    }

}
