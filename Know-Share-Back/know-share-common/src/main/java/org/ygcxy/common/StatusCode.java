package org.ygcxy.common;

import lombok.Getter;

/**
 * @Project: org.ygcxy.common
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 20:35
 * @Description:
 */
@Getter
public enum StatusCode {
    SUCCESS(200, "Success","success"),
    BAD_REQUEST(400, "Bad Request","error"),
    UNAUTHORIZED(401, "Unauthorized","error"),
    NOT_FOUND(404, "Not Found","error"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error","error"),
    ADVERTISE_ADD_ERROR(521,"Add Advertise Error!","error"),
    ADVERTISE_UPDATE_ERROR(522,"Update Advertise Error!", "error"),
    ADVERTISE_DELETE_ERROR(523,"Delete Advertise Error!", "error"),
    ADVERTISE_FOUND_ERROR(524, "Advertise Not Found!", "error"),
    SEARCH_SUCCESS(200,"Success Find Result","success"),
    SEARCH_ERROR(409,"Search Fail","error"),
    HASH_CODE_ERROR(290,"已发送验证码","error"),
    EMAIL_NO_CODE_ERROR(290,"尚未发送验证码","error"),
    VERIFY_CODE_ERROR(290, "验证码错误", "error"),
    REGISTER_SUCCESS(200,"注册成功","success"),
    HAS_EMAIL_ERROR(290,"邮箱已存在","error"),
    SEND_CODE(200,"验证码已发送","success"),
    REGISTER_ERROR(240,"User already exists","error"),
    FIND_USER_ERROR(260,"Not Found Target User Info.","success"),
    FIND_USER_SUCCESS(200,"Find Target User Info Success", "success"),
    DELETE_USER_SUCCESS(200,"Success Delete User","success"),
    DELETE_USER_ERROR(299,"Delete User Exception","error");


    private final int code;
    private final String message;
    private final String status;

    StatusCode(int code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

}
