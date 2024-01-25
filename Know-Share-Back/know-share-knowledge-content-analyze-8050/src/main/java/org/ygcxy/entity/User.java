package org.ygcxy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/24 22:39
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user")
public class User implements Serializable {
    private Long userId;
    private String userNickname;
    private String userEmail;
    private String userPassword;
    private String userGender;
    private Boolean isUserBan;
    private String userAvatarUrl;
    private Date userCreateTime;

}
