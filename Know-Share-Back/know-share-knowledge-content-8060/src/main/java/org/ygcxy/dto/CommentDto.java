package org.ygcxy.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class CommentDto {
    private Long commentsId;
    private String userNickname; //用户的昵称
    private String userAvatarUrl; //用户头像地址
    private String commentsContent; //评论内容
    private Date commentsTime;
    private List<CommentDto> childComment;
    private Long kcId;
    private Long userId;
}
