package org.ygcxy.dto;

import lombok.Data;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 13:10
 * @Description:
 */
@Data
public class VerifyCodeRequest {
    private String email;
    private String code;
}
