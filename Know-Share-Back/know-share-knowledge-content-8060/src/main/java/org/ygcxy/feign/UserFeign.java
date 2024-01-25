package org.ygcxy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.ygcxy.comment.Application;
import org.ygcxy.entity.User;

/**
 * @Project: org.ygcxy.feign
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/12 10:13
 * @Description:
 */
@FeignClient(url = "http://localhost:8020",name = "user")
public interface UserFeign {
    @GetMapping(Application.API_VERSION + "/cloud/user/id/{id}")
    User findUserById (@PathVariable Long id);
}
