package org.ygcxy.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

/**
 * @Project: org.ygcxy.service
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 16:04
 * @Description:
 */
public interface JWTService {
    /**
     * 生成Token
     * @param userDetails
     * @return token值
     */
    String generateToken(UserDetails userDetails);

    /**
     * 从token中提取Email
     * @param token
     * @return email
     */
    String extractEmail(String token);
    /**
     * 从token中提取所有数据
     * @param token
     * @param claimsTFunction
     * @return
     * @param <T>
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsTFunction);

    /**
     * 校验token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    void deleteToken(String username);

}
