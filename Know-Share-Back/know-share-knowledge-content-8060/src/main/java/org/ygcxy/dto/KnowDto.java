package org.ygcxy.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class KnowDto {
    @TableId(type = IdType.AUTO)
    private Long kcId; //内容id
    private String kcTitle; //内容标题
    private String kcOverview; //内容大纲
    private UserDto user;
    private Date kcPublishTime; //知识发布时间
    private List<String> kcCategory; //知识分类
    private Boolean isKcBan; //知识是否发布
    private Boolean isKcShow; //是否显示文章
    private String content;
}
