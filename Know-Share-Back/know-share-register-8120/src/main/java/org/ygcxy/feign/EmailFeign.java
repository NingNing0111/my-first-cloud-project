package org.ygcxy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.ygcxy.common.Application;
import org.ygcxy.dto.VerifyCodeRequest;

/**
 * @Project: org.ygcxy.feign
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 13:00
 * @Description:
 */
@FeignClient(url = "http://localhost:8070", name = "email")
public interface EmailFeign {
    @GetMapping(Application.API_VERSION + "/email/verify")
    Boolean verify(@RequestBody VerifyCodeRequest request);
    @GetMapping(Application.API_VERSION + "/email/code/{email}")
    Boolean checkCode(@PathVariable String email);
}
