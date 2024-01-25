package org.ygcxy;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.ygcxy.entity.SensitiveLog;
import org.ygcxy.repository.SensitiveLogRepository;

import java.util.List;

/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/23 15:33
 * @Description:
 */
@SpringBootTest
public class SensitiveApplicationTests {
    @Autowired
    SensitiveLogRepository repository;
    @Test
    public void logTest(){
        List<SensitiveLog> all = repository.findAll();
        System.out.println(all);
    }
}
