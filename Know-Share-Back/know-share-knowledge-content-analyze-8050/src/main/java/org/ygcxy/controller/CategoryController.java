package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ygcxy.comment.Application;
import org.ygcxy.comment.ResponseResult;
import org.ygcxy.service.CategoryService;

/**
 * @Project: org.ygcxy.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/24 22:34
 * @Description:
 */
@RequestMapping(Application.API_VERSION + "/category")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    /**
     * 显示用户标签总量
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseResult findCategory(@PathVariable("userId") Long userId){
        return categoryService.findCategory(userId);
    }

}
