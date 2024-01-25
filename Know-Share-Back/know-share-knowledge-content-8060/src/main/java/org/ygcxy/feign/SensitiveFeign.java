package org.ygcxy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ygcxy.comment.Application;
import org.ygcxy.dto.SensitiveServiceResponse;

/**
 * @Project: org.ygcxy.feign
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/12 13:08
 * @Description:
 */
@FeignClient(url = "http://localhost:8030", name = "sensitive")
public interface SensitiveFeign {
    @PostMapping(Application.API_VERSION + "/cloud/sensitive/check")
    SensitiveServiceResponse check(@RequestBody String text);
}
