package org.ygcxy.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.swing.text.StyledEditorKit;
import java.util.Date;
import java.util.List;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 14:31
 * @Description:
 */
@Document(indexName = "knowledge_content")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class KnowledgeContent {
    @Id
    private String id;
    @Field(type = FieldType.Long)
    private Long kcId;
    @Field(type = FieldType.Text,store = true, analyzer = "ik_smart",searchAnalyzer = "ik_max_word")
    private String kcTitle;
    @Field(type = FieldType.Text, store = true,analyzer = "ik_smart",searchAnalyzer = "ik_max_word")
    private String kcOverview;
    @Field(type = FieldType.Long)
    private Long userId;
    @Field(type = FieldType.Date, pattern = "uuuu-MM-dd HH:mm:ss")
    private Date kcPublishTime;
    @Field(type = FieldType.Keyword)
    private List<String> kcCategory;
    @Field(type = FieldType.Boolean)
    private Boolean isKcShow;
    @Field(type = FieldType.Text, store = true,analyzer = "ik_smart",searchAnalyzer = "ik_max_word")
    private String content;
    @Field(type = FieldType.Long)
    private Long likes = 0L;
    @Field
    private Long collect = 0L;
    @Field
    private Long comment = 0L;
    @Field(type = FieldType.Long)
    private Long view;
    private String image;
    private String originContent;
    private Boolean isSensitive;











}
