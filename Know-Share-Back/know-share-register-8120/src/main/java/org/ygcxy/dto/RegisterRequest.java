package org.ygcxy.dto;

import lombok.Data;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 11:38
 * @Description:
 */
@Data
public class RegisterRequest {
    private String nickname;
    private String email;
    private String password;
    private String code;
    private String gender;
}
