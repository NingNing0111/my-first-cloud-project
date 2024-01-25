package org.ygcxy.service;

import org.ygcxy.common.ResponseResult;
import org.ygcxy.entity.UserMessage;

/**
 * @Project: org.ygcxy.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/25 14:41
 * @Description:
 */

public interface UserMessageService {
    // 删除消息
    ResponseResult deleteById(Long id);
    // 分页查询消息
    ResponseResult queryByUserId(Integer page, Integer size, Long userId);
    // 修改已读状态
    ResponseResult changeStatus(Long id);
}
