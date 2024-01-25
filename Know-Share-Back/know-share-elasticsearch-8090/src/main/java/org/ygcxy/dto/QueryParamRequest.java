package org.ygcxy.dto;

import lombok.Data;

import java.util.List;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/24 09:48
 * @Description:
 */
@Data
public class QueryParamRequest {
    private String title;
    private String overview;
    private Long userId;

}
