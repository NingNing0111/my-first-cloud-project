package org.ygcxy.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDto {
    @TableId(type = IdType.AUTO)
    private Long userId; //用户的id
    private String userNickname; //用户的昵称
    private String userAvatarUrl; //用户头像地址
    private String gender; //用户性别

}
