package org.ygcxy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ygcxy.common.RedisPrefix;
import org.ygcxy.dto.VerifyCodeRequest;
import org.ygcxy.logs.RecordLogs;
import org.ygcxy.service.RedisService;
import org.ygcxy.service.VerifyCodeService;
import org.ygcxy.utils.RandomStringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 20:49
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class VerifyCodeServiceImpl implements VerifyCodeService {

    private final RecordLogs recordLogs;
    private final RedisService redisService;
    @Override
    public String generateCode(String targetEmail, Integer len, Long liveTime) {
        String code = RandomStringUtils.generateRandomString(len);
        redisService.setWithExpiration(targetEmail + RedisPrefix.EMAIL_CODE,code,liveTime, TimeUnit.MINUTES);
        return code;
    }

    @Override
    public Boolean checkCode(VerifyCodeRequest verifyCodeRequest) {
        String targetEmail = verifyCodeRequest.getEmail();
        String code = verifyCodeRequest.getCode();
        try {
            String cacheCode = (String) redisService.get(targetEmail + RedisPrefix.EMAIL_CODE);
            redisService.delete(targetEmail+RedisPrefix.EMAIL_CODE);
            return code.equals(cacheCode);
        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(),"Email Service","System Error");
            return false;
        }

    }

    @Override
    public void deleteCode(String targetEmail) {
        redisService.delete(targetEmail + RedisPrefix.EMAIL_CODE);
    }

    @Override
    public Boolean hasCode(String targetEmail) {

        return redisService.hasKey(targetEmail + RedisPrefix.EMAIL_CODE);
    }
}
