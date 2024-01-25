package org.ygcxy.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.ygcxy.common.RabbitMqConstant;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.common.StatusCode;
import org.ygcxy.dto.AuthenticationRequest;
import org.ygcxy.dto.AuthenticationResponse;
import org.ygcxy.entity.User;
import org.ygcxy.feign.UserFeign;
import org.ygcxy.logs.RecordLogs;
import org.ygcxy.service.AuthenticationService;
import org.ygcxy.service.JWTService;

import java.util.Date;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 16:50
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserFeign userFeign;
    private final JWTService jwtService;
    private final RecordLogs recordLogs;
    @Override
    public ResponseResult authenticate(AuthenticationRequest authenticationRequest) {
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           authenticationRequest.getEmail(),
                           authenticationRequest.getPassword())
           );
           User user = userFeign.findByEmail(authenticationRequest.getEmail());
           String jwtToken = jwtService.generateToken(user);
           recordLogs.recordSystemLogs("Login Success", RabbitMqConstant.WORK_USER_LOGS_QUEUE,"Login");
           AuthenticationResponse rsp = AuthenticationResponse.builder()
                   .token(jwtToken)
                   .id(user.getUserId())
                   .roles(user.getUserRoles())
                   .username(user.getUsername())
                   .email(user.getUserEmail())
                   .createTime(user.getUserCreateTime())
                   .gender(user.getUserGender())
                   .avatarUrl(user.getUserAvatarUrl())
                   .build();
           return ResponseResult
                   .setCommonStatusAndData(StatusCode.SUCCESS, rsp);
       }catch (Exception e){
           e.printStackTrace();
           recordLogs.recordSystemLogs(e.getMessage(), RabbitMqConstant.WORK_USER_LOGS_QUEUE, "Login");
           return ResponseResult.setCommonStatus(StatusCode.AUTHENTICATION_ERROR);
       }
    }
}
