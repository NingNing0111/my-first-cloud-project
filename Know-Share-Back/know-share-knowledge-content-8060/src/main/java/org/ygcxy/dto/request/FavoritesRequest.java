package org.ygcxy.dto.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class FavoritesRequest {
    @TableId(type = IdType.AUTO)
    private Long userId;
    private Long kcId;
}
