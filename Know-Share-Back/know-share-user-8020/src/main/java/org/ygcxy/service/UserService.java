package org.ygcxy.service;

import org.ygcxy.common.ResponseResult;

/**
 * @Project: org.ygcxy.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 10:11
 * @Description:
 */
public interface UserService {
    /**
     * 根据用户邮箱查询用户信息
     * @param email
     * @return
     */
    ResponseResult findUserByEmail(String email);

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    ResponseResult findUserById(Long id);

    /**
     * 查询所有用户
     * @return
     */
    ResponseResult findAllUsers();

    /**
     * 根据ID删除用户
     * @param id
     * @return
     */
    ResponseResult deleteUserById(Long id);

    /**
     * 根据用户邮箱删除用户
     * @param email
     * @return
     */
    ResponseResult deleteUserByEmail(String email);

    ResponseResult allUserPage(Integer page, Integer size, String sortType,Boolean isBan);

    ResponseResult banUser(Long id);

    ResponseResult findById(Long id);

}

