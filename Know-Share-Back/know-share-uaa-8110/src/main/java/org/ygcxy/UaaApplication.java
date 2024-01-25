package org.ygcxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/7 18:51
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients
public class UaaApplication {
    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class,args);
    }
}
