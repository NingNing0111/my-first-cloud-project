package org.ygcxy.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.ygcxy.dto.SensitiveLogsRequest;
import org.ygcxy.entity.SensitiveLog;
import org.ygcxy.repository.SensitiveLogRepository;
import org.ygcxy.service.SensitiveLogsService;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/23 15:48
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SensitiveLogsServiceImpl implements SensitiveLogsService {
    private final SensitiveLogRepository logRepository;

    @Override
    public void addLogInfo(SensitiveLogsRequest sensitiveLogsRequest) {
        try {
            SensitiveLog logs = SensitiveLog.builder()
                    .isSensitive(sensitiveLogsRequest.getIsSensitive())
                    .origin(sensitiveLogsRequest.getOrigin())
                    .kcId(sensitiveLogsRequest.getUserId())
                    .build();
            logRepository.save(logs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
