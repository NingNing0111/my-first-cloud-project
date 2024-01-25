package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.constant.Application;
import org.ygcxy.constant.ResponseResult;
import org.ygcxy.constant.StatusCode;
import org.ygcxy.service.LogsService;

import java.util.List;

/**
 * @Project: org.ygcxy.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 16:21
 * @Description:
 */
@RestController
@RequestMapping(Application.API_VERSION + "/logs")
@Slf4j
@RequiredArgsConstructor
public class LogsController {
    private final LogsService logsService;
    @GetMapping("/user/page/{page}/size/{size}")
    public ResponseResult getUserLogsPage(
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size
    ){
        try {
            return logsService.findAllPageUser(page,size);

        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/system/page/{page}/size/{size}")
    public ResponseResult getSystemLogsPage(
            @PathVariable("page") Integer page,
            @PathVariable("size") Integer size
    ){
        try {
            return logsService.findAllPageSystem(page,size);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete/batch")
    public ResponseResult deleteSystemLogs(
            @RequestBody List<Long> ids
    ){
        return logsService.deleteSystemLogsById(ids);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteSystemLogs(
            @PathVariable Long id
    ){
        return logsService.deleteSystemLogs(id);
    }
}
