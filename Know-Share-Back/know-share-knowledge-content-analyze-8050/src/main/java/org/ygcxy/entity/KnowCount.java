package org.ygcxy.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/23 23:25
 * @Description:
 */
@Data
@Builder
public class KnowCount {
    private String month;
    private Long count;
}
