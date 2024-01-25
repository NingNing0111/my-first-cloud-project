package org.ygcxy.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.transform.Result;

/**
 * @Project: org.ygcxy.common
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 18:54
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
        return new ResponseResult<>(resultCode.getCode(), resultCode.getMessage(), data, resultCode.getStatus());
    }
    public static ResponseResult setCommonStatus(StatusCode statusCode){
        return new ResponseResult<>(statusCode.getCode(), statusCode.getMessage(),null,statusCode.getStatus());
    }
}
