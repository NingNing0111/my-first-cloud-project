package org.ygcxy.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: org.ygcxy.config
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 16:11
 * @Description:
 */
@Configuration
@Data
public class JWTConfig {
    @Value("${application.security.jwt.secretKey}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private Long jwtExpiration;
}
