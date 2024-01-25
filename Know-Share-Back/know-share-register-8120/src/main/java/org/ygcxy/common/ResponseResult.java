package org.ygcxy.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Project: org.ygcxy.common
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 19:35
 * @Description:
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class ResponseResult<T> {
    private Integer code;
    private String message;
    private T data;
    private String status;
    public static <T> ResponseResult<T> setCommonStatusAndData(StatusCode resultCode,T data){
        return new ResponseResult<>(resultCode.getCode(), resultCode.getMessage(), data, resultCode.getMessage());
    }
    public static ResponseResult setCommonStatus(StatusCode statusCode){
        return new ResponseResult<>(statusCode.getCode(), statusCode.getMessage(),null,statusCode.getMessage());
    }
}
