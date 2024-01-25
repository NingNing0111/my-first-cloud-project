package org.ygcxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.ygcxy.entity.User;
import org.ygcxy.repository.UserRepository;

import java.util.List;

/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 20:23
 * @Description:
 */
@SpringBootTest
public class UserApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void pageTest(){
        Pageable page = PageRequest.of(0, 10, Sort.by("userId").ascending());
        Page<User> userPage = userRepository.findAll(page);
        System.out.println(userPage.toString());
    }
    @Test
    public void findUsersBan(){
        Long i = userRepository.countUsersByIsUserBan(true);
        System.out.println(i);
    }
    @Test
    public void countUsersNotBan(){
        Long i = userRepository.countUsersByIsUserBan(false);
        System.out.println(i);
    }
    @Test
    public void countUsers(){
        Long i = userRepository.count();
        System.out.println(i);
    }

    @Test
    public void insertUsers() {
    }

    @Test
    public void findUserById() {
        User userByUserId = userRepository.findUserByUserId(2L);
        System.out.println(userByUserId);
    }



}
