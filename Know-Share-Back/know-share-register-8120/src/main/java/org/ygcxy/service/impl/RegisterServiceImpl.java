package org.ygcxy.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.ygcxy.common.RabbitMqConstant;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.common.StatusCode;
import org.ygcxy.dto.*;
import org.ygcxy.entity.User;
import org.ygcxy.entity.UserRole;
import org.ygcxy.feign.EmailFeign;
import org.ygcxy.feign.UserFeign;
import org.ygcxy.logs.RecordLogs;
import org.ygcxy.service.RegisterService;

import java.sql.Date;
import java.util.List;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 13:07
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterServiceImpl implements RegisterService {
    private final RabbitTemplate rabbitTemplate;
    private final UserFeign userFeign;
    private final EmailFeign emailFeign;
    private final RecordLogs recordLogs;



    @Override
    public ResponseResult registerNewUser(RegisterRequest register) {
        // 验证用户是否存在
        User registerUser = userFeign.findByEmail(register.getEmail());
        if(registerUser != null){
            return ResponseResult.setCommonStatus(StatusCode.HASH_CODE_ERROR);
        }
        if(!emailFeign.checkCode(register.getEmail())){
            return ResponseResult.setCommonStatus(StatusCode.EMAIL_NO_CODE_ERROR);
        }
        // 检查验证码是否正确
        VerifyCodeRequest verifyCodeRequest = new VerifyCodeRequest();
        verifyCodeRequest.setCode(register.getCode());
        verifyCodeRequest.setEmail(register.getEmail());
        if(!emailFeign.verify(verifyCodeRequest)){
            return ResponseResult.setCommonStatus(StatusCode.VERIFY_CODE_ERROR);
        }
        // 添加用户
        BCryptPasswordEncoder password = new BCryptPasswordEncoder();
        try {
            User user = User.builder()
                    .userNickname(register.getNickname())
                    .userEmail(register.getEmail())
                    .userPassword(password.encode(register.getPassword()))
                    .userRoles(List.of(UserRole.USER))
                    .userCreateTime(new Date(System.currentTimeMillis()))
                    .userGender(register.getGender())
                    .build();
            userFeign.addUser(user);
            return ResponseResult.setCommonStatus(StatusCode.REGISTER_SUCCESS);
        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(),"Register Service","System Error");
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseResult sendCode(String email) {
        try {
            User user = userFeign.findByEmail(email);
            if(user != null){
                return ResponseResult.setCommonStatus(StatusCode.HAS_EMAIL_ERROR);
            }
            rabbitTemplate.convertAndSend(RabbitMqConstant.WORK_EMAIL_QUEUE,email);
            return ResponseResult.setCommonStatus(StatusCode.SEND_CODE);
        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(),"Register Service","System Error");

            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }
}
