package org.ygcxy.controller.pre;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ygcxy.common.Application;
import org.ygcxy.dto.SensitiveLogsRequest;
import org.ygcxy.dto.SensitiveServiceResponse;
import org.ygcxy.service.SensitiveLogsService;
import org.ygcxy.service.SensitiveWordService;

/**
 * @Project: org.ygcxy.controller.pre
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 10:11
 * @Description:
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(Application.API_VERSION + "/cloud/sensitive")
public class CloudCheckController {
    private final SensitiveWordService service;
    private final SensitiveLogsService sensitiveLogs;
    @PostMapping("/check")
    public SensitiveServiceResponse check(@RequestBody String text){
        return service.sensitiveService(text);
    }

    @PostMapping("/add")
    public void add(
            @RequestBody SensitiveLogsRequest request
    ){
        sensitiveLogs.addLogInfo(request);
    }
}
