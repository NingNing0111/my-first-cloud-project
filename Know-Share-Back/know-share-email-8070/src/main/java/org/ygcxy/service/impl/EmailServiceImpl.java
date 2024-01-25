package org.ygcxy.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.ygcxy.common.StatusCode;
import org.ygcxy.config.EmailConfig;
import org.ygcxy.dto.EmailServiceResponse;
import org.ygcxy.dto.Response;
import org.ygcxy.logs.RecordLogs;
import org.ygcxy.service.EmailService;
import org.ygcxy.service.VerifyCodeService;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 20:44
 * @Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final EmailConfig emailConfig;
    private final VerifyCodeService verifyCodeService;
    private final RecordLogs recordLogs;
    private final JavaMailSender javaMailSender;
 @Override
    public Response sendVerifyCode(String targetEmail) {
     String code = verifyCodeService.generateCode(targetEmail,EmailConfig.codeLength, emailConfig.getLiveTime());
     SimpleMailMessage message = new SimpleMailMessage();
     message.setFrom(emailConfig.getFromEmail());
     message.setSubject(EmailConfig.EMAIL_HEADER + " 注册信息");
     message.setText("尊敬的用户，您的注册验证码为:" + code + ",请在5分钟以内使用，并且妥善保管，不要分享给其他人！");
     message.setTo(targetEmail);
     try{
         javaMailSender.send(message);
         return EmailServiceResponse.builder()
                 .code(StatusCode.SUCCESS.getCode())
                 .message("验证码已发送")
                 .status(StatusCode.SUCCESS.getStatus()).build();
     }catch (Exception e){
         recordLogs.recordSystemLogs(e.getMessage(),"Email Service","System Error");
         return EmailServiceResponse.builder()
                 .code(StatusCode.INTERNAL_SERVER_ERROR.getCode())
                 .message("验证码发送异常")
                 .status(StatusCode.INTERNAL_SERVER_ERROR.getStatus())
                 .build();
     }
    }
}
