package org.ygcxy.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Project: org.ygcxy.config
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 20:22
 * @Description:
 */
@Configuration
@Data
public class EmailConfig {
    public final static String EMAIL_HEADER = "Know Share";
    public final static Integer codeLength = 6;
    @Value("${spring.mail.live-time}")
    private Long liveTime;
    @Value("${spring.mail.protocol}")
    private String protocol;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${spring.mail.password}")
    private String fromPassword;
}
