package org.ygcxy.common;

import lombok.Getter;

/**
 * @Project: org.ygcxy.common
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 14:05
 * @Description:
 */
@Getter
public enum StatusCode {
    SUCCESS(200, "Success", "success"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "error"),

    ADVERTISE_ADD_ERROR(521,"Add Advertise Error!","error"),
    ADVERTISE_UPDATE_ERROR(522,"Update Advertise Error!", "error"),
    ADVERTISE_DELETE_ERROR(523,"Delete Advertise Error!", "error"),
    ADVERTISE_FOUND_ERROR(524, "Advertise Not Found!", "error");
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

    public String getStatus() {
        return status;
    }

}
