package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.common.Application;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.service.UserMessageService;

/**
 * @Project: org.ygcxy.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/25 14:40
 * @Description:
 */
@RestController
@RequestMapping(Application.API_VERSION + "/meesage")
@RequiredArgsConstructor
public class UserMessageController {

    private final UserMessageService messageService;

    @GetMapping("/{userId}/page/{page}/size/{size}")
    public ResponseResult queryPage(
            @PathVariable Long userId,
            @PathVariable Integer page,
            @PathVariable Integer size
    ){
        return messageService.queryByUserId(page,size,userId);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(
            @PathVariable Long id
    ){
        return messageService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseResult update(
            @PathVariable Long id
    ) {
        return messageService.changeStatus(id);
    }
}
