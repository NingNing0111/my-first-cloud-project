package org.ygcxy.dto.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class KnowDtoRequest {
    private Long kcId; //内容id
    private String kcTitle; //内容标题
    private String kcOverview; //内容大纲
    private Long userId; //作者id
    private List<String> kcCategory; //知识分类
    private String content;
    private Boolean isKcShow;
    private Boolean isPublish;
    private String kcImage;
}
