package org.ygcxy.comment;

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

    NOT_SHOW(200,"Not Found", "success"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error","error"),
    COLLECT_SUCCESS(200,"收藏成功","success"),
    UN_COLLECT_SUCCESS(200,"取消收藏成功","success"),
    LIKES_SUCCESS(200,"点赞成功","success"),
    UN_LIKES_SUCCESS(200,"取消点赞成功","success"),
    COMMENTS_SUCCESS(200,"评论成功","success"),
    DELETE_COMMENTS_SUCCESS(200,"删除评论成功","success"),
    DELETE_KNOW_SUCCESS(200,"删除文章成功","success"),
    ADD_IS_SENSITIVE_KNOW_SUCCESS(200,"存在敏感内容，平台已过滤","warning"),
    ADD_KNOW_SUCCESS(200,"内容发表成功","success"),
    DELETE_KNOW_WARNING(200,"文章已删除","error"),
    NOT_FOUND_IMAGE(200,"照片不能为空","error"),
    UPDATE_SUCCESS(200,"更新成功","success"),
    REPORT_ALREADY(200,"已经举报", "success"),
    REPORT_SUCCESS(200,"举报成功","success"),
    BAN_SUCCESS(200,"封禁成功","success"),
    COMMENT_SUCCESS(200,"评论成功","success");
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
