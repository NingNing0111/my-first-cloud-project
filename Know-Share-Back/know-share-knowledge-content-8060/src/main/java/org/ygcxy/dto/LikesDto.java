package org.ygcxy.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class LikesDto {
    @TableId(type = IdType.AUTO)
    private Long likeId; //点赞id
    private Long userId; //点赞用户
    private Long kcId; //点赞知识内容id intoID
    private Date likeTime; //点赞时间
    private Boolean isLikeShow; //是否点赞  status
}
