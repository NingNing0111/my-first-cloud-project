package org.ygcxy.logs;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.ygcxy.comment.RabbitMqConstant;

import java.sql.Date;

/**
 * @Project: org.ygcxy.logs
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 19:58
 * @Description:
 */
@RequiredArgsConstructor
@Component
public class RecordLogs {
    private final RabbitTemplate rabbitTemplate;
    public void recordSystemLogs(String message,String source,String level){
        SystemLog systemLog = SystemLog.builder()
                .date(new Date(System.currentTimeMillis()))
                .source(source)
                .level(level)
                .message(message)
                .build();
        rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_SYSTEM_LOGS_QUEUE, systemLog);
    }

}
