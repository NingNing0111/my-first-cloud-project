package org.ygcxy.common;

import lombok.Getter;

/**
 * @Project: org.ygcxy.common
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 10:08
 * @Description:
 */
@Getter
public enum StatusCode {

    SUCCESS(200, "Success", "success"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "error");

    private final int code;
    private final String message;
    private final String status;

    StatusCode(int code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
