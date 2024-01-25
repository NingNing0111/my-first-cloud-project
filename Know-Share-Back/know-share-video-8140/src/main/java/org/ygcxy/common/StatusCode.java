package org.ygcxy.common;

/**
 * @Project: org.ygcxy.common
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 10:22
 * @Description:
 */
public enum StatusCode {
    SUCCESS(200, "Success","success"),
    BAD_REQUEST(400, "Bad Request","error"),
    UNAUTHORIZED(401, "Unauthorized","error"),
    NOT_FOUND(404, "Not Found","error"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error","error"),
    REGISTER_ERROR(240,"User already exists","error"),
    FIND_USER_ERROR(260,"Not Found Target User Info.","success"),
    FIND_USER_SUCCESS(200,"Find Target User Info Success", "success"),
    DELETE_USER_SUCCESS(200,"Success Delete User","success"),
    DELETE_USER_ERROR(299,"Delete User Exception","error"),
    INSERT_FILE_ERROR(400,"Insert File Error","error"),
    NOT_FOUND_FILE(400,"Not Found File","error"),

    BAN_USER_SUCCESS(200,"用户状态修改成功","success");
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
