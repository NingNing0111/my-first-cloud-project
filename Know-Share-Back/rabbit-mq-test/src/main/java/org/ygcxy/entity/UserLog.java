package org.ygcxy.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 18:13
 * @Description:
 */
@Data
@Builder
public class UserLog {
    private Long id;
    private Date date;
    private Long userId;
    private String description;
    private String type;
}
