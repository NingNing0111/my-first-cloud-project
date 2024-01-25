package org.ygcxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ygcxy.entity.User;
import org.ygcxy.feign.UserFeign;


/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 15:08
 * @Description:
 */
@SpringBootTest
public class UaaApplicationTests {

    @Autowired
    private UserFeign userFeign;


    @Test
    public void feignTest(){
        User byEmail = userFeign.findByEmail("zndcode@gmail.com");
        System.out.println(byEmail);
    }
}
