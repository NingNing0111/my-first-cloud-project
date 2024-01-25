package org.ygcxy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.common.Application;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.service.UserAnalyzeService;

@Slf4j
@RestController
@RequestMapping(Application.API_VERSION + "/analyzeuser")
@RequiredArgsConstructor
public class UserAnalyzeController {
    private final UserAnalyzeService userAnalyzeService;

    /**
     * 返回用户总数
     * @return
     */
    @GetMapping("/user/count")
    public ResponseResult countUser (
    ){
        return userAnalyzeService.countUser();
    }

    /**
     * 返回某年每月的用户注册量
     * @param year
     * @return
     */
    @GetMapping("/register/year/{year}")
    public ResponseResult countRegisterByYear(
            @PathVariable int year
    ){
        return userAnalyzeService.countByYear(year);
    }

}
