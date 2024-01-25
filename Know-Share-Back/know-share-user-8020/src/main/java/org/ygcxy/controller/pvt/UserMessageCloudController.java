package org.ygcxy.controller.pvt;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ygcxy.common.Application;
import org.ygcxy.entity.UserMessage;
import org.ygcxy.repository.UserMessageRepository;

/**
 * @Project: org.ygcxy.controller.pvt
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/25 14:39
 * @Description:
 */
@RestController
@RequestMapping(Application.API_VERSION + "/cloud/message")
@RequiredArgsConstructor
public class UserMessageCloudController {
    private final UserMessageRepository messageRepository;

    @PostMapping("/")
    public void add(@RequestBody UserMessage message) {
        messageRepository.save(message);
    }
}
