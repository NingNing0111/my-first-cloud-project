package org.ygcxy.dto.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Request {
    private Long reporterId; //举报人ID
    private Long kcId; //被举报知识内容id
    private String content; //举报内容

}
