package org.ygcxy.logs;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ygcxy.common.OperationType;
import org.ygcxy.common.RabbitMqConstant;
import org.ygcxy.entity.UserLog;

import java.util.Date;

/**
 * @Project: org.ygcxy.logs
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 18:21
 * @Description:
 */
@SpringBootTest
public class UserLogsTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendUserLogsTest(){
        UserLog userLogs = UserLog.builder()
                .userId(26L)
                .description("这是用户的第二条测试日志")
                .type(OperationType.TEST)
                .date(new Date(System.currentTimeMillis()))
                .build();
        rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_USER_LOGS_QUEUE, userLogs);


    }
}
