package org.ygcxy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/11 09:38
 * @Description:
 */
@Entity
@Table(name = "sensitive_words")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SensitiveWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
