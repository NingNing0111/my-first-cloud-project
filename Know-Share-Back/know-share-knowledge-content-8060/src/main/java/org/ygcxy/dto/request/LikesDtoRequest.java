package org.ygcxy.dto.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class LikesDtoRequest {
    private Long userId; //点赞用户
    private Long kcId; //点赞知识内容id
}
