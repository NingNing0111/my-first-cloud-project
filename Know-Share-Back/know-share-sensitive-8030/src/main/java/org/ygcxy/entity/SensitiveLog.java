package org.ygcxy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/23 15:27
 * @Description:
 */
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SensitiveLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long kcId;
    private String origin;
    private Boolean isSensitive;
}

