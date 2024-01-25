package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ygcxy.comment.Application;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.service.AnalyzeUserService;

/**
 * @Project: org.ygcxy.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/24 22:46
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping(Application.API_VERSION + "/userknow")
@RequiredArgsConstructor
public class AnalyzeUserKnowController {
    private final AnalyzeUserService analyzeUserService;
    @GetMapping("/{userId}")
    public ResponseResult analyzeUserCount(
            @PathVariable Long userId
    ){
        return analyzeUserService.analyzeUserCount(userId);
    }
}
