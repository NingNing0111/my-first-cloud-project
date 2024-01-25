package org.ygcxy.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @Project: org.ygcxy.dto
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 01:15
 * @Description:
 */
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class VerifyCodeResponse extends Response{
    private Boolean isOk;
}
