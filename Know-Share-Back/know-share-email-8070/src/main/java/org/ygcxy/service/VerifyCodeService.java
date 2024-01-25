package org.ygcxy.service;

import org.ygcxy.dto.Response;
import org.ygcxy.dto.VerifyCodeRequest;

/**
 * @Project: org.ygcxy.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 20:45
 * @Description:
 */
public interface VerifyCodeService {
    /**
     * 生成目标邮箱指定长度的验证码
     * @param targetEmail
     * @param len
     * @return 验证码
     */
    String generateCode(String targetEmail, Integer len, Long liveTime);

    /**
     * 验证指定邮箱指定验证码是否通过
     * @return
     */
    Boolean checkCode(VerifyCodeRequest verifyCodeRequest);

    /**
     * 删除邮箱验证码
     * @param targetEmail
     */
    void deleteCode(String targetEmail);
    Boolean hasCode(String targetEmail);

}
