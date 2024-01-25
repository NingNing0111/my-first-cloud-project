package org.ygcxy;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 15:12
 * @Description:
 */
@SpringBootTest
public class AdvertiseApplicationTests {
    @Test
    public void dateTest(){
        Date date = new Date(System.currentTimeMillis());
        System.out.println(JSON.toJSONString(date));
    }
}
