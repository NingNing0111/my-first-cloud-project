package org.ygcxy.utils;

import io.minio.MinioClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.ygcxy.config.MinioProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * minio连接客户端
 */
@Component
@Slf4j
@EnableConfigurationProperties(MinioProperties.class)
public class MinioUtils {
    @Resource
    private MinioClient minioClient;



}
