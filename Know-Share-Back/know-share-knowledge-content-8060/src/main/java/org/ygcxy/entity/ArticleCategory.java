package org.ygcxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("article_category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleCategory {
    @TableId(type = IdType.AUTO)
    private Long relationId; //关联id
    private Long kcId; //知识内容id
    private Long categoryId; //分类id
}
