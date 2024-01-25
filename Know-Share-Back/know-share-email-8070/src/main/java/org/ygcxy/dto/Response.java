package org.ygcxy.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 20:41
 * @Description:
 */
@SuperBuilder
@Data
public abstract class Response {
    private Integer code;
    private String status;
    private String message;
}
