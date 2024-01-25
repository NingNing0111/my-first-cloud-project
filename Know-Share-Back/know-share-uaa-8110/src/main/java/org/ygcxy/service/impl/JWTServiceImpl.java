package org.ygcxy.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.ygcxy.common.RedisPrefix;
import org.ygcxy.config.JWTConfig;
import org.ygcxy.logs.RecordLogs;
import org.ygcxy.service.JWTService;
import org.ygcxy.service.RedisService;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @Project: org.ygcxy.service.impl
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 16:06
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {
    private final JWTConfig jwtConfig;
    private final RedisService redisService;
    private final RecordLogs recordLogs;
    @Override
    public String generateToken(UserDetails userDetails) {
        String jwt = buildToken(new HashMap<>(), userDetails, jwtConfig.getJwtExpiration());
        redisService.setWithExpiration(userDetails.getUsername() + RedisPrefix.USER_TOKEN.name(),jwt,jwtConfig.getJwtExpiration(), TimeUnit.MILLISECONDS);
        return jwt;
    }
    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ){
        long currTime = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(currTime))
                .setExpiration(new Date(currTime + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtConfig.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }


    @Override
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            recordLogs.recordSystemLogs(e.getMessage(),"Uaa Service","SystemError");
            return null;
        }

    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractEmail(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token) && (redisService.get(userDetails.getUsername()+RedisPrefix.USER_TOKEN.name()) != null);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public void deleteToken(String username) {
        redisService.delete(username+RedisPrefix.USER_TOKEN.name());
    }
}
