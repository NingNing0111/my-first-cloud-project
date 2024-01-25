package org.ygcxy.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentsTime;
    private Long isParentCommentId;
}
