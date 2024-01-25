package org.ygcxy.common;

/**
 * @Project: org.ygcxy.common
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 16:58
 * @Description:
 */
public enum StatusCode {
    SUCCESS(200, "Success","success"),
    BAD_REQUEST(400, "Bad Request","error"),
    UNAUTHORIZED(401, "Unauthorized","error"),
    NOT_FOUND(404, "Not Found","error"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error","error"),
    REGISTER_ERROR(240,"User already exists","error"),
    AUTHENTICATION_ERROR(509,"身份校验异常","error"),
    LOGIN_SUCCESS(200,"登录成功","success");
    private final int code;
    private final String message;
    private final String status;

    StatusCode(int code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus(){
        return status;
    }

}
