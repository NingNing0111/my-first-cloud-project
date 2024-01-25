package org.ygcxy.service;

import org.ygcxy.common.ResponseResult;
import org.ygcxy.dto.RegisterRequest;

/**
 * @Project: org.ygcxy.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 12:57
 * @Description:
 */
public interface RegisterService {
    ResponseResult registerNewUser(RegisterRequest register);
    ResponseResult sendCode(String email);
}
