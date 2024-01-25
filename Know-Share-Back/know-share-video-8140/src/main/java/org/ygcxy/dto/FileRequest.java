package org.ygcxy.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/23 09:29
 * @Description:
 */
@Data
public class FileRequest {
    private MultipartFile file;
    private Long userId;
}
