package org.ygcxy.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.ygcxy.service.RedisService;

import java.util.concurrent.TimeUnit;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 16:16
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, Object data) {
        redisTemplate.opsForValue().set(key,data);
    }

    @Override
    public void setWithExpiration(String key, Object value, Long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key,value,timeout,unit);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
