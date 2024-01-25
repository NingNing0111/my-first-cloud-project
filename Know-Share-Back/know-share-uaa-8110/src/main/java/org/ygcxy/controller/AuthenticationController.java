package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ygcxy.common.Application;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.common.StatusCode;
import org.ygcxy.dto.AuthenticationRequest;
import org.ygcxy.service.AuthenticationService;

/**
 * @Project: org.ygcxy.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 16:54
 * @Description:
 */
@RestController
@RequestMapping(Application.API_VERSION + "/uaa")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping(value = "/auth")
    public ResponseResult authenticate(@RequestBody AuthenticationRequest request) {
       log.info("请求参数:{}",request);
        try {
            return authenticationService.authenticate(request);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }
}
