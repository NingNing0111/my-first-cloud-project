package org.ygcxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ygcxy.handler.type.ListToVarcharTypeHandler;

import java.util.List;

@Data
@Builder
@TableName(value="user",autoResultMap = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Long userId; //用户的id
    private String userNickname; //用户的昵称
    private String userEmail; //用户的邮箱
    private String userPassword; //用户的密码
    private Boolean isUserBan; //是否被禁用
    @TableField(typeHandler = ListToVarcharTypeHandler.class)
    private List<String> userRoles; //用户角色列表
    private String userAvatarUrl; //用户头像地址
    private String gender; //用户性别
}
