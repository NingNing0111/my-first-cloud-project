package org.ygcxy.pvt;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ygcxy.repository.UserRepository;
import org.ygcxy.service.UserService;

/**
 * @Project: org.ygcxy.pvt
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 22:28
 * @Description:
 */
@SpringBootTest
public class UserCloudControllerTests {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Test
    public void findUserByIdTest(){
        System.out.println(userRepository.findUserByUserId(9L));
    }
    @Test
    public void findUserByEmailTest(){
        System.out.println(userRepository.findUserByUserEmail("zdncode@gmail.com"));
    }
}
