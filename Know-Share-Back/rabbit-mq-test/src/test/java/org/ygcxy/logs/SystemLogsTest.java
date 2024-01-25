package org.ygcxy.logs;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ygcxy.common.RabbitMqConstant;
import org.ygcxy.entity.SystemLog;

import java.util.Date;


/**
 * @Project: org.ygcxy.logs
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 18:14
 * @Description:
 */
@SpringBootTest
public class SystemLogsTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void sendLogsTest(){
        SystemLog log = SystemLog.builder()
                .level("INFO")
                .source("Test")
                .message("这是第二条测试日志")
                .date(new Date(System.currentTimeMillis()))
                .build();
        rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_SYSTEM_LOGS_QUEUE, log);
    }

}
