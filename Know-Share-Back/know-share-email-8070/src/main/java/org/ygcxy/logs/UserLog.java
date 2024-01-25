package org.ygcxy.logs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Project: org.ygcxy.logs
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 19:59
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLog {
    private Long id;
    private Date date;
    private Long userId;
    private String description;
    private String type;
}
