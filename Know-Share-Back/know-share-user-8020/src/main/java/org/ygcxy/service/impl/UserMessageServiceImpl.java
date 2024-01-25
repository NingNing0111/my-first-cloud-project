package org.ygcxy.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.common.StatusCode;
import org.ygcxy.entity.UserMessage;
import org.ygcxy.repository.UserMessageRepository;
import org.ygcxy.service.UserMessageService;

import java.util.Optional;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/25 14:44
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserMessageServiceImpl implements UserMessageService {
    private final UserMessageRepository messageRepository;



    @Override
    public ResponseResult deleteById(Long id) {
        try {
            messageRepository.deleteById(id);
            return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult queryByUserId(Integer page, Integer size, Long userId) {
        try {
            Page<UserMessage> data = messageRepository.findAllByUserId(PageRequest.of(page, size), userId);
            return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,data);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult changeStatus(Long id) {
        try {

        UserMessage message = messageRepository.findUserMessageById(id);
        message.setIsRead(true);
        messageRepository.save(message);
        return ResponseResult.setCommonStatus(StatusCode.SUCCESS);
        }catch (Exception e){
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }

    }
}
