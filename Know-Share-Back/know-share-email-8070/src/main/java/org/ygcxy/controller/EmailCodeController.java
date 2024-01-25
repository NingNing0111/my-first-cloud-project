package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.common.Application;
import org.ygcxy.dto.VerifyCodeRequest;
import org.ygcxy.service.VerifyCodeService;

/**
 * @Project: org.ygcxy.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 01:12
 * @Description:
 */
@RestController
@RequestMapping(Application.API_VERSION + "/email")
@RequiredArgsConstructor
@Slf4j
public class EmailCodeController {
    private final VerifyCodeService verifyCodeService;
    @PostMapping("/verify")
    public Boolean verify(@RequestBody VerifyCodeRequest verifyCodeRequest){
        log.info("接收到验证码验证请求：{} ===> {}",verifyCodeRequest.getEmail(),verifyCodeRequest.getCode());
        return verifyCodeService.checkCode(verifyCodeRequest);
    }
    @GetMapping("/code/{email}")
    public Boolean checkEmailCode(@PathVariable String email){
        return verifyCodeService.hasCode(email);

    }
}
