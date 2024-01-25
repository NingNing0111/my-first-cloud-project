package org.ygcxy;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/7 21:55
 * @Description:
 */
@SpringBootApplication
public class ElasticsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class,args);
    }
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
