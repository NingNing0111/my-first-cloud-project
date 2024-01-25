package org.ygcxy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/10 11:12
 * @Description:
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Advertise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String targetUrl;
    private Date startDate;
    private Date endDate;
    private Timestamp createAt;
    private Boolean isShow;

    @PrePersist
    public void setDefaultValue(){
        if(isShow == null){
            isShow = false;
        }
        if(createAt == null){
            createAt = new Timestamp(System.currentTimeMillis());
        }
    }
}
