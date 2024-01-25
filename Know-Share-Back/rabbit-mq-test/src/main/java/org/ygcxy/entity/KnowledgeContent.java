package org.ygcxy.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 20:25
 * @Description:
 */
@Data
@Builder
public class KnowledgeContent {
    private String id;
    private Long kcId;
    private String kcTitle;
    private String overview;
    private Long userId;
    private Date knPublishTime;
    private List<String> kcCategory;
    private Boolean isKcPublish;
    private Boolean isKcShow;
    private String content;
}
