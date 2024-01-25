package org.ygcxy;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.ygcxy.service.FileService;
import org.ygcxy.utils.CheckFileTypeUtils;

/**
 * @Project: org.ygcxy
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/23 09:20
 * @Description:
 */
@SpringBootTest
@RequiredArgsConstructor
public class VideoApplicationTest {
    private final FileService fileService;
    @Test
    public void fileTypeCheckTest(){

    }
}
