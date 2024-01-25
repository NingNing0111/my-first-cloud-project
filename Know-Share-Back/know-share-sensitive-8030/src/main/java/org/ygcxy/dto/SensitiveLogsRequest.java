package org.ygcxy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/23 15:46
 * @Description:
 */
@Data
public class SensitiveLogsRequest {
    private Long userId;
    private String origin;
    private Boolean isSensitive;
}

