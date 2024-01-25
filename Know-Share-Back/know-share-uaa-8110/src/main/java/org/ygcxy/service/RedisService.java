package org.ygcxy.service;

import java.util.concurrent.TimeUnit;

/**
 * @Project: org.ygcxy.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 16:04
 * @Description:
 */
public interface RedisService {
    // 存储数据至Redis中
    void set(String key, Object data);
    // 存储数据至Redis中并设置失效时间
    void setWithExpiration(String key, Object value, Long timeout, TimeUnit unit);
    // 从Redis中获取数据
    Object get(String key);
    // 从Redis中删除数据
    void delete(String key);
    // 校验redis中是否有该key
    boolean hasKey(String key);
}
