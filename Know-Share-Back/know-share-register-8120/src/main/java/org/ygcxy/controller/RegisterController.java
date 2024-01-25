package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.common.Application;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.dto.RegisterRequest;
import org.ygcxy.service.RegisterService;

/**
 * @Project: org.ygcxy.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 13:19
 * @Description:
 */
@RestController
@RequestMapping(Application.API_VERSION + "/register")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;
    @GetMapping("/code/{email}")
    public ResponseResult sendCode(@PathVariable String email){
        return registerService.sendCode(email);
    }
    @PostMapping("/new")
    public ResponseResult register(@RequestBody RegisterRequest request){
        return registerService.registerNewUser(request);

    }
}
