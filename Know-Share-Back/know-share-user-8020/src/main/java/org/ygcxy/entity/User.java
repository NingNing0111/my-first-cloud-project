package org.ygcxy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/9 10:08
 * @Description:
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String userNickname;
    private String userEmail;
    private String userPassword;
    private String userGender;
    private Boolean isUserBan;
    @ElementCollection
    @CollectionTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @JsonIgnore
    private List<UserRole> userRoles;
    private String userAvatarUrl;
    private Date userCreateTime;
    @PrePersist
    public void setDefaultValues(){
        if(isUserBan == null){
            isUserBan = false;
        }
        if(userAvatarUrl == null){
            userAvatarUrl = "http://38.54.12.85:9000/zxy/Know Share-ape-logo.png";
        }
    }
}
