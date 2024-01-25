package org.ygcxy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/25 14:27
 * @Description:
 */
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserMessage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 消息ID
    private Long userId; // 该消息属于的用户ID
    private Long kcId; // 知识内容ID
    private UserMessageType type; // 消息类型
    @Builder.Default
    private Boolean isRead = false; // 是否已读
}
