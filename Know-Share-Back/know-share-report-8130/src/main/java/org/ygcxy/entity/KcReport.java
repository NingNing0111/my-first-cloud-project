package org.ygcxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "kc_reports")
public class KcReport {
    @TableId(type = IdType.AUTO)
    private Long id; //举报id
    private Long reporterId; //举报人ID
    private Long kcId; //被举报知识内容id
    private String content; //举报内容
    private Date reportTime; //举报时间
    @Builder.Default
    private Boolean isHandle = false; // 是否处理过了
}
