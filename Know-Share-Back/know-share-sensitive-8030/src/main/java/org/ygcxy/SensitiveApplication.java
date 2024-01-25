package org.ygcxy;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import toolgood.words.StringSearch;


/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @Date: 2024/1/7 16:58
 * @Description:
 */
@SpringBootApplication
@RequiredArgsConstructor
public class SensitiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(SensitiveApplication.class,args);
    }

    @Bean
    public StringSearch stringSearch(){
        return new StringSearch();
    }

}
