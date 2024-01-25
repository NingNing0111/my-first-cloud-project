package org.ygcxy.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/21 19:42
 * @Description:
 */
@Data
@Builder
public class RMDto {
    private Long kcId;
    private Boolean isOk;
}
