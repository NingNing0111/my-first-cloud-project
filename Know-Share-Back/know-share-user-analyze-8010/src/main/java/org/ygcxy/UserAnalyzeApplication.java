package org.ygcxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @Date: 2024/1/7 16:58
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients
public class UserAnalyzeApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAnalyzeApplication.class,args);
    }
}
