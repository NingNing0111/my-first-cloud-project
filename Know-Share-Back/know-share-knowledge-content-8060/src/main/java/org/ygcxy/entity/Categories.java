package org.ygcxy.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categories {
    @TableId(type = IdType.AUTO)
    private Long categoryId; //分类id
    private String categoryName; //分类名称
}
