package org.ygcxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

@Data
@Builder
@TableName("likes")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class Likes {
    @TableId(type = IdType.AUTO)
    private Long likeId; //点赞id
    private Long userId; //点赞用户
    private Long kcId; //点赞知识内容id
    private Date likeTime; //点赞时间
    private Boolean isLikeShow; //是否点赞

}
