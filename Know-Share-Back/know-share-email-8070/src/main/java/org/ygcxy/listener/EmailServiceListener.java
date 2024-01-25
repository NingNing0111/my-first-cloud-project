package org.ygcxy.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.ygcxy.service.EmailService;

import static org.ygcxy.common.RabbitMqConstant.WORK_EMAIL_QUEUE;

/**
 * @Project: org.ygcxy.listener
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 22:10
 * @Description:
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class EmailServiceListener {
    private final EmailService emailService;
    @RabbitListener(queues = WORK_EMAIL_QUEUE)
    public void sendEmailListener(String targetEmail){
        log.info("来自" + WORK_EMAIL_QUEUE + "队列的消息 =====> {}",targetEmail);
        try {
            emailService.sendVerifyCode(targetEmail);

        }catch (Exception e){
            log.info("发送邮箱出现异常:{}",e.getMessage());
        }
    }
}
