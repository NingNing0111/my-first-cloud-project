package org.ygcxy.dto;

import lombok.Data;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 16:46
 * @Description:
 */
@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
