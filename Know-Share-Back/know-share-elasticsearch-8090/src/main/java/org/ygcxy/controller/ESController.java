package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.common.Application;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.common.StatusCode;
import org.ygcxy.dto.QueryParamRequest;
import org.ygcxy.service.KnowledgeContentService;

import java.util.Collections;
import java.util.Objects;

/**
 * @Project: org.ygcxy.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 16:44
 * @Description:
 */
@RestController
@RequestMapping(Application.API_VERSION + "/es")
@RequiredArgsConstructor
@Slf4j
public class ESController {
    private final KnowledgeContentService knowledgeContentService;
    @GetMapping("/content/page/{page}/size/{size}")
    public ResponseResult findKnByContent(
            @RequestParam String keyword,
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return knowledgeContentService.findByContent(keyword,page,size);
    }

    @GetMapping("/overview/page/{page}/size/{size}")
    public ResponseResult findKnByOverview(
            @RequestParam String keyword,
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return knowledgeContentService.findByOverview(keyword,page,size);
    }

    @GetMapping("/title/page/{page}/size/{size}")
    public ResponseResult findKnByTitle(
            @RequestParam String keyword,
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return knowledgeContentService.findByTitle(keyword,page,size);
    }

    @GetMapping("/category/page/{page}/size/{size}")
    public ResponseResult findKcByCategorys(
            @RequestParam String keyword,
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return knowledgeContentService.findByKcCategory(Collections.singletonList(keyword),page,size);
    }

    @GetMapping("/page/{page}/size/{size}")
    public ResponseResult findAll(
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return knowledgeContentService.findAll(page,size);
    }

    @GetMapping("/{id}")
    public ResponseResult findById(
            @PathVariable Long id
    ){
       return knowledgeContentService.findById(id);
    }

    @GetMapping("/view/page/{page}/size/{size}")
    public ResponseResult findHotPage(
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
            return knowledgeContentService.findHotPage(page,size);
    }

    @GetMapping("/comment/page/{page}/size/{size}")
    public ResponseResult findComment(
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return knowledgeContentService.findCommentPage(page, size);
    }

    @GetMapping("/collect/page/{page}/size/{size}")
    public ResponseResult findCollect(
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return knowledgeContentService.findCollectPage(page,size);
    }

    @GetMapping("/likes/page/{page}/size/{size}")
    public ResponseResult findLikes(
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return knowledgeContentService.findLikesPage(page,size);
    }

    @GetMapping("/{userId}/page/{page}/size/{size}")
    public ResponseResult findPageByUserId(
            @PathVariable Long userId,
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return knowledgeContentService.findPageByUserId(userId, page, size);
    }

    @GetMapping("/query/page/{page}/size/{size}")
    public ResponseResult query(
            @PathVariable Integer page,
             @PathVariable Integer size,
             QueryParamRequest request
            ){
        return knowledgeContentService.queryByParam(
                page,size,request
        );
    }
}
