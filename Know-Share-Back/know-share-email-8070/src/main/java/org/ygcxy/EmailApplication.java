package org.ygcxy;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @Date: 2024/1/7 16:56
 * @Description:
 */
@SpringBootApplication
public class EmailApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class,args);
    }
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
