package org.ygcxy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ygcxy.common.Application;

/**
 * @Project: org.ygcxy.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 17:32
 * @Description:
 */
@RestController
@RequestMapping(Application.API_VERSION +"/uaa/demo")
public class AuthenticationDemoController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }
}
