package org.ygcxy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 11:41
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long userId;
    private String userNickname;
    private String userEmail;
    private String userPassword;
    private String userGender;
    private Boolean isUserBan = false;
    private List<UserRole> userRoles;
    private String userAvatarUrl;
    private Date userCreateTime;
}
