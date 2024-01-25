package org.ygcxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.ygcxy.handler.type.ListToVarcharTypeHandler;

import java.sql.Date;
import java.util.List;

@Data
@Builder
@TableName(value="knowledge_content",autoResultMap = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
public class KnowledgeContent {
    @TableId(type = IdType.AUTO)
    private Long kcId; //内容id
    private String kcTitle; //内容标题
    private String kcOverview; //内容大纲
    private Long userId; //作者id
    private Date kcPublishTime; //知识发布时间
    @TableField(value = "kc_category",typeHandler = ListToVarcharTypeHandler.class)
    private List<String> kcCategory; //知识分类
    @TableField(value = "is_kc_publish")
    private Boolean isKcBan; //知识是否发布
    private Boolean isKcShow; //是否显示文章
    private String content;
    private String image;
    private int view;
    private String originContent;
    private Boolean isSensitive;

}
