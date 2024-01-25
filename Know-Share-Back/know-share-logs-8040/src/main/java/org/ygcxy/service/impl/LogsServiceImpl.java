package org.ygcxy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.ygcxy.constant.ResponseResult;
import org.ygcxy.constant.StatusCode;
import org.ygcxy.entity.SystemLog;
import org.ygcxy.entity.UserLog;
import org.ygcxy.repository.SystemLogRepository;
import org.ygcxy.repository.UserLogRepository;
import org.ygcxy.service.LogsService;

import java.util.List;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 16:15
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class LogsServiceImpl implements LogsService {
    private final UserLogRepository userLogRepository;
    private final SystemLogRepository systemLogRepository;
    @Override
    public ResponseResult findAllPageUser(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "date");
        Page<UserLog> userLogs = userLogRepository.findAll(pageRequest);
        return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,userLogs);
    }

    @Override
    public void addUserLog(UserLog userLog) {
        userLogRepository.save(userLog);
    }

    @Override
    public void addSystemLog(SystemLog systemLog) {
        systemLogRepository.save(systemLog);
    }

    @Override
    public ResponseResult findAllPageSystem(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "date");
        Page<SystemLog> systemLogs = systemLogRepository.findAll(pageRequest);
        return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,systemLogs);
    }

    @Override
    public ResponseResult deleteSystemLogsById(List<Long> ids) {
        try {
            systemLogRepository.deleteAllByIdInBatch(ids);
            return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult deleteSystemLogs(Long id) {
        try {
            systemLogRepository.deleteById(id);
            return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }
}
