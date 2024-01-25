package org.ygcxy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 15:53
 * @Description:
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private Date date;
    private Long userId;
    private String description;
    private String type;
}
