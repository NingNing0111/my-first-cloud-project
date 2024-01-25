package org.ygcxy.service;

import org.ygcxy.dto.Response;

/**
 * @Project: org.ygcxy.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 20:31
 * @Description:
 */
public interface EmailService {
    /**
     * 发送指定长度的邮箱验证码
     * @param targetEmail
     * @return
     */
    Response sendVerifyCode(String targetEmail);
}
