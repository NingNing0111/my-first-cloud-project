package org.ygcxy.service;

import org.ygcxy.constant.ResponseResult;
import org.ygcxy.entity.SystemLog;
import org.ygcxy.entity.UserLog;

import java.util.List;

/**
 * @Project: org.ygcxy.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 16:12
 * @Description:
 */
public interface LogsService {
    /**
     * 分页查找所有的用户日志
     * @param page
     * @param size
     * @return
     */
     ResponseResult findAllPageUser(Integer page, Integer size);

    /**
     * 添加用户日志
     * @param userLog
     */
    void addUserLog(UserLog userLog);

    /**
     * 添加系统日志
     * @param systemLog
     */
    void addSystemLog(SystemLog systemLog);

    /**
     * 分页查找所有的系统日志消息
     * @param page
     * @param size
     * @return
     */
    ResponseResult findAllPageSystem(Integer page, Integer size);

    ResponseResult deleteSystemLogsById(List<Long> ids);

    ResponseResult deleteSystemLogs(Long id);
}
