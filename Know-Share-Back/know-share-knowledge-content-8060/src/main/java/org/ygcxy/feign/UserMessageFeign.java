package org.ygcxy.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ygcxy.comment.Application;
import org.ygcxy.entity.UserMessage;

/**
 * @Project: org.ygcxy.feign
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/25 15:03
 * @Description:
 */
@FeignClient(url = "http://localhost:8020", name = "message")
public interface UserMessageFeign {
    @PostMapping(Application.API_VERSION + "/cloud/message")
    void add(@RequestBody UserMessage userMessage);
}
