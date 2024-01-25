package org.ygcxy.dto.request;

import lombok.Data;
import org.ygcxy.entity.Comments;

import java.util.Date;
import java.util.List;

@Data
public class CommentsRequest {
    private Long userId;
    private Long kcId; //文章id
    private Long isParentCommentId; //父评论id
    private String commentsContent; //评论内容

}
