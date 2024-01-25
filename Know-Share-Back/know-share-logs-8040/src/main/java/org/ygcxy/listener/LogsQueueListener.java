package org.ygcxy.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.ygcxy.constant.RabbitMqConstant;
import org.ygcxy.entity.SystemLog;
import org.ygcxy.entity.UserLog;
import org.ygcxy.service.LogsService;

/**
 * @Project: org.ygcxy.listener
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 16:26
 * @Description:
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class LogsQueueListener {
    private final LogsService logsService;
    @RabbitListener(queues = RabbitMqConstant.WORK_SYSTEM_LOGS_QUEUE)
    public void saveSystemLogs(SystemLog systemLog){
//        logsService.addSystemLog(systemLog);
        System.out.println(systemLog);
    }
    @RabbitListener(queues = RabbitMqConstant.WORK_USER_LOGS_QUEUE)
    public void saveUserlogs(UserLog userLog){
        logsService.addUserLog(userLog);
    }
}
