package org.ygcxy.dto.request;

import lombok.Data;

@Data
public class CategoriesRequest {
    private Long categoryId; //分类id
    private String categoryName; //分类名称
}
