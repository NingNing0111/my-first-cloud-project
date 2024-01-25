package org.ygcxy.service;

import org.ygcxy.common.ResponseResult;
import org.ygcxy.dto.AuthenticationRequest;

/**
 * @Project: org.ygcxy.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 16:45
 * @Description:
 */
public interface AuthenticationService {
    ResponseResult authenticate(AuthenticationRequest authenticationRequest);
}
