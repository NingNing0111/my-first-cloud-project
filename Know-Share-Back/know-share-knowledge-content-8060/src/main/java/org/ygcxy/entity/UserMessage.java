package org.ygcxy.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/25 15:02
 * @Description:
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMessage {
    private Long id; // 消息ID
    private Long userId; // 该消息属于的用户ID
    private Long kcId; // 知识内容ID
    private UserMessageType type; // 消息类型
    @Builder.Default
    private Boolean isRead = false; // 是否已读
}
