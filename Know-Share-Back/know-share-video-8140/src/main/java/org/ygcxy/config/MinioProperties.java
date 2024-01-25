package org.ygcxy.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {
    private String endpoint; //通信地址

    @Value(value = "${minio.access-key}")
    private String accessKey; //用户登录名

    private String secretKey; //用户密码

    private String bucketName;

    private String fileHost; //文件地址

}
