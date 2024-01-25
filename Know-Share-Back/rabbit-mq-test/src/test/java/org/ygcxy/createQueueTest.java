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
 * @Date: 2024/1/10 18:06
 * @Description:
 */
@SpringBootTest
public class createQueueTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void createLogsQueue(){
        Queue queueOfSystemLogs = new Queue(RabbitMqConstant.WORK_SYSTEM_LOGS_QUEUE);
        Queue queueOfUserLogs = new Queue(RabbitMqConstant.WORK_USER_LOGS_QUEUE);
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        rabbitAdmin.declareQueue(queueOfSystemLogs);
        rabbitAdmin.declareQueue(queueOfUserLogs);

    }

    @Test
    public void createESQueue(){
        Queue esAdd = new Queue(RabbitMqConstant.WORK_ES_ADD_CONTENT_QUEUE);
        Queue esUpdate = new Queue(RabbitMqConstant.WORK_ES_UPDATE_CONTENT_QUEUE);
        Queue esDelete = new Queue(RabbitMqConstant.WORK_ES_DELETE_CONTENT_QUEUE);
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        rabbitAdmin.declareQueue(esAdd);
        rabbitAdmin.declareQueue(esUpdate);
        rabbitAdmin.declareQueue(esDelete);
    }
    @Test
    public void createQueue(){
        Queue queue = new Queue(RabbitMqConstant.WORK_ES_LIKES_CONTENT_QUEUE);
        Queue queue1 = new Queue(RabbitMqConstant.WORK_ES_COLLECT_CONTENT_QUEUE);
        Queue queue2 = new Queue(RabbitMqConstant.WORK_ES_COMMENT_CONTENT_QUEUE);
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareQueue(queue1);
        rabbitAdmin.declareQueue(queue2);
    }
}
