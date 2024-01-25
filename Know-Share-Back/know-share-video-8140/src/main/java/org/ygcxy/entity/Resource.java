package org.ygcxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "resource", autoResultMap = true)
public class Resource {
    @TableId(type = IdType.AUTO)
    private Long id; //资源id
    private Long userId; //用户ID
    private String url; //资源地址
    private String type; //资源类型
    private String name; // 资源名
}
