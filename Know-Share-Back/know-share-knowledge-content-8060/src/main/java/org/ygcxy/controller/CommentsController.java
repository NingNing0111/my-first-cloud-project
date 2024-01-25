package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.dto.request.CommentsRequest;
import org.ygcxy.service.CommentsService;
import org.ygcxy.comment.Application;

@RestController
@Slf4j
@RequestMapping(Application.API_VERSION + "/comments")
@RequiredArgsConstructor
public class CommentsController {
    private final CommentsService commentsService;

    @GetMapping("/")
    public ResponseResult getList(){
        return commentsService.getList();
    }

    @GetMapping("/{kcId}")
    public ResponseResult getCommentByKcId(
            @PathVariable Long kcId
    ){

        return commentsService.getCommentByKcId(kcId);
    }

    @GetMapping("/parent/{parentId}")
    public ResponseResult getCommentByParentId(
            @PathVariable Long parentId,
            @RequestBody CommentsRequest request

    ){
        return commentsService.getCommentByparentId(request);
    }

    @PostMapping("/")
    public ResponseResult addComment(@RequestBody CommentsRequest request){
        return commentsService.addComment(request);
    }
}
