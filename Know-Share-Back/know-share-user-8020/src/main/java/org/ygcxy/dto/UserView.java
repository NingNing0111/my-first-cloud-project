package org.ygcxy.dto;

import com.alibaba.druid.filter.AutoLoad;
import lombok.Builder;
import lombok.Data;
import org.ygcxy.entity.UserRole;

import java.lang.ref.PhantomReference;
import java.util.Date;
import java.util.List;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 10:17
 * @Description:
 */
@Data
@Builder
public class UserView {
    private Long id;
    private String username;
    private String email;
    private String avatarUrl;
    private String gender;
    private Date createTime;
    private List<UserRole> roles;
    private Boolean isBan;
}
