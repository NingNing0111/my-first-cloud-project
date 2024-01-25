package org.ygcxy;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ygcxy.common.RabbitMqConstant;


/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 22:34
 * @Description:
 */
@SpringBootTest
public class EmailApplicationTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void rabbitSendTest(){
        RabbitAdmin admin = new RabbitAdmin(rabbitTemplate);
        Queue queue = new Queue(RabbitMqConstant.WORK_EMAIL_QUEUE);
        admin.declareQueue(queue);
    }
}
