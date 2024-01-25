package org.ygcxy;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ygcxy.common.RabbitMqConstant;
import org.ygcxy.dto.RMDto;
import org.ygcxy.entity.KnowledgeContent;

import java.util.List;

/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 01:25
 * @Description:
 */
@SpringBootTest
public class RabbitTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void emailMessageTest(){
        rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_EMAIL_QUEUE, "zdncode@gmail.com");
    }
    @Test
    public void AddKnowledgeContentTest() {

        rabbitTemplate.convertAndSend(
                RabbitMqConstant.WORK_ES_ADD_CONTENT_QUEUE,
                KnowledgeContent.builder()
                        .content("消息队列监听到的内容")
                        .overview("这是大纲")
                        .kcTitle("这是标题哦")
                        .userId(26L)
                        .kcCategory(List.of("消息队列","RabbitMQ"))
                        .build()
                );


    }


    @Test
    public void ESTest(){

        rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_ES_DELETE_CONTENT_QUEUE,15L);
    }

    @Test
    public void updateEsTest(){
        KnowledgeContent know = KnowledgeContent.builder().userId(26L).build();
        rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_ES_UPDATE_CONTENT_QUEUE,know);
    }

    @Test
    public void addViewTest() {
        RMDto build = RMDto.builder().kcId(46L).isOk(true).build();
        rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_ES_COMMENT_CONTENT_QUEUE,build);
    }

}
