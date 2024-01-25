package org.ygcxy.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 09:42
 * @Description:
 */
@Builder
@Data
public class SensitiveServiceResponse {
    private Boolean isSensitive;
    private String statement;
}
