package org.ygcxy.dto;

import lombok.Builder;
import lombok.Data;
import org.ygcxy.entity.UserRole;

import java.sql.Date;
import java.util.List;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/17 18:05
 * @Description:
 */
@Data
@Builder
public class AuthenticationResponse {
    private Long id;
    private String token;
    private String username;
    private String email;
    private String avatarUrl;
    private String gender;
    private Date createTime;
    private List<UserRole> roles;
}
