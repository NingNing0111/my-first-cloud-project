package org.ygcxy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Project: org.ygcxy.entity
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 14:55
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    private Long userId;
    private String userNickname;
    private String userEmail;
    private String userPassword;
    private String userGender;
    private Boolean isUserBan;
    private List<UserRole> userRoles;
    private String userAvatarUrl;
    private Date userCreateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();
        for (UserRole userRole: this.userRoles){
            authoritiesList.add(new SimpleGrantedAuthority(userRole.name()));
        }
        return authoritiesList;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.isUserBan;
    }
}




