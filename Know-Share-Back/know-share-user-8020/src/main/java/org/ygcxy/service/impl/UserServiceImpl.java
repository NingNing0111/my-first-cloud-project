package org.ygcxy.service.impl;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ygcxy.common.RabbitMqConstant;
import org.ygcxy.common.ResponseResult;
import org.ygcxy.common.StatusCode;
import org.ygcxy.dto.UserView;
import org.ygcxy.entity.User;
import org.ygcxy.logs.RecordLogs;
import org.ygcxy.repository.UserRepository;
import org.ygcxy.service.UserService;

import java.util.HashMap;
import java.util.List;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 10:19
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    private final RecordLogs recordLogs;
    private final UserRepository userRepository;
    @Override
    public ResponseResult findUserByEmail(String email) {
        User user = userRepository.findUserByUserEmail(email);
        if(user == null){
            return ResponseResult.setCommonStatus(StatusCode.FIND_USER_ERROR);
        }
            UserView view = UserView.builder()
                    .id(user.getUserId())
                    .email(user.getUserEmail())
                    .username(user.getUserNickname())
                    .avatarUrl(user.getUserAvatarUrl())
                    .roles(user.getUserRoles())
                    .gender(user.getUserGender())
                    .createTime(user.getUserCreateTime())
                    .isBan(user.getIsUserBan())
                    .build();
            recordLogs.recordSystemLogs("Find User By Email", RabbitMqConstant.WORK_USER_LOGS_QUEUE,"User Search");
            return ResponseResult.setCommonStatusAndData(StatusCode.FIND_USER_SUCCESS,view);
    }

    @Override
    public ResponseResult findUserById(Long id) {
        User user = userRepository.findUserByUserId(id);
        if(user == null){
            return ResponseResult.setCommonStatus(StatusCode.FIND_USER_ERROR);
        }
        UserView view = UserView.builder()
                .id(user.getUserId())
                .email(user.getUserEmail())
                .username(user.getUserNickname())
                .avatarUrl(user.getUserAvatarUrl())
                .roles(user.getUserRoles())
                .gender(user.getUserGender())
                .createTime(user.getUserCreateTime())
                .isBan(user.getIsUserBan())
                .build();
        recordLogs.recordSystemLogs("Find User By Id", RabbitMqConstant.WORK_USER_LOGS_QUEUE,"User Search");

        return ResponseResult.setCommonStatusAndData(StatusCode.FIND_USER_SUCCESS,view);
    }

    @Override
    public ResponseResult findAllUsers() {
        return null;
    }

    @Override
    public ResponseResult deleteUserById(Long id) {
        try {
            userRepository.deleteUserByUserId(id);
            recordLogs.recordSystemLogs("Delete User By Id" , RabbitMqConstant.WORK_USER_LOGS_QUEUE,"User Warning");
            return ResponseResult.setCommonStatus(StatusCode.DELETE_USER_SUCCESS);
        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(),"User Service","System Error");
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult deleteUserByEmail(String email) {
        try {
            userRepository.deleteUserByUserEmail(email);
            recordLogs.recordSystemLogs("Delete User By Email", RabbitMqConstant.WORK_USER_LOGS_QUEUE,"User Warning");

            return ResponseResult.setCommonStatus(StatusCode.DELETE_USER_SUCCESS);
        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(),"User Service","System Error");
            return ResponseResult.setCommonStatus(StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseResult allUserPage(Integer page, Integer size, String sortType,Boolean isBan) {
        Sort.Direction direction = Sort.Direction.ASC;
        long total = 0L;
        Page<User> userPage = null;
        if (!StringUtils.isBlank(sortType)) {
            if(sortType.equals("desc")){
                direction = Sort.Direction.DESC;
            }
        }
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction,"userId"));
        if(isBan == null){
            total = userRepository.count();
            userPage = userRepository.findAll(pageRequest);
        }else{
            total = userRepository.countUsersByIsUserBan(isBan);
            userPage = userRepository.findUsersByIsUserBan(pageRequest,isBan);
        }

        List<UserView> userViewPage = userPage.stream().map(user ->
                UserView.builder()
                        .id(user.getUserId())
                        .username(user.getUserNickname())
                        .email(user.getUserEmail())
                        .avatarUrl(user.getUserAvatarUrl())
                        .gender(user.getUserGender())
                        .createTime(user.getUserCreateTime())
                        .roles(user.getUserRoles())
                        .isBan(user.getIsUserBan())
                        .build()
        ).toList();
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("total",total);
        dataMap.put("users",userViewPage);
        recordLogs.recordSystemLogs("Search User For Page", RabbitMqConstant.WORK_USER_LOGS_QUEUE,"User Search");
        return ResponseResult.setCommonStatusAndData(StatusCode.SUCCESS,dataMap);
    }

    @Override
    public ResponseResult banUser(Long id) {
        // 先查id
        User userByUserId = userRepository.findUserByUserId(id);
        // 修改状态
        userByUserId.setIsUserBan(!userByUserId.getIsUserBan());
        // 保存
        userRepository.save(userByUserId);
        return ResponseResult.setCommonStatus(StatusCode.BAN_USER_SUCCESS);
    }

    @Override
    public ResponseResult findById(Long id) {
        User userByUserId = userRepository.findUserByUserId(id);
        return ResponseResult.setCommonStatusAndData(
                StatusCode.FIND_USER_SUCCESS,
                UserView.builder()
                        .id(userByUserId.getUserId())
                        .username(userByUserId.getUserNickname())
                        .email(userByUserId.getUserEmail())
                        .avatarUrl(userByUserId.getUserAvatarUrl())
                        .roles(userByUserId.getUserRoles())
                        .createTime(userByUserId.getUserCreateTime())
                        .gender(userByUserId.getUserGender())
                        .isBan(userByUserId.getIsUserBan())
                        .build()
        );
    }


}
