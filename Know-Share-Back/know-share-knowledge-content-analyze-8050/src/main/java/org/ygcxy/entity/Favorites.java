package org.ygcxy.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("favorites")
public class Favorites {
    @TableId(type = IdType.AUTO)
    private Long favoritesId;
    private Long userId;
    private Long kcId;
    private Date favoriteTime;
    private Boolean isFavoritesShow;
}
