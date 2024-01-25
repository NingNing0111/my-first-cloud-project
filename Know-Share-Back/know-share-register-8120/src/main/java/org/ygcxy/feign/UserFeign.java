package org.ygcxy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.ygcxy.common.Application;
import org.ygcxy.entity.User;

/**
 * @Project: org.ygcxy.feign
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 12:50
 * @Description:
 */
@FeignClient(url = "http://localhost:8020",name = "user")
public interface UserFeign {
    @GetMapping(Application.API_VERSION + "/cloud/user/email/{email}")
    User findByEmail(@PathVariable("email") String email);
    @PostMapping(Application.API_VERSION + "/cloud/user/add")
    void addUser(@RequestBody User user);
}
