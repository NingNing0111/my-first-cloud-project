package org.ygcxy.dto;

import lombok.Data;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 10:12
 * @Description:
 */
@Data
public class UserRequest {
    private Long id;
    private String nickname;
    private String email;
    private String gender;
}
