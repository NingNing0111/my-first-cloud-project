package org.ygcxy.config;

import io.minio.MinioClient;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 传统连接minio
 */
@Data
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfig {
    @Resource
    private MinioProperties minioProperties;

    /**
     * 注入minio
     * @return
     */
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }

}
