package org.ygcxy.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ygcxy.dto.CommentDto;

import java.util.Date;
import java.util.List;

@Data
@TableName("comments")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comments {
    @TableId(type = IdType.AUTO)
    private Long commentsId;
    private Long userId;
    private Long kcId;
    private String commentsContent;
    private Date commentsTime;
    @Builder.Default
    private Long isParentCommentId = 0L;
}
