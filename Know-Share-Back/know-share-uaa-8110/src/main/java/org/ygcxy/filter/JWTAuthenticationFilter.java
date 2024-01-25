package org.ygcxy.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.ygcxy.service.JWTService;

import java.io.IOException;

/**
 * @Project: org.ygcxy.filter
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/1/8 16:32
 * @Description:
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("authorization");
        final String jwt;
        final String username;
        log.info("{}",authHeader);
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            log.info("请求头不存在");
            filterChain.doFilter(request,response);
            return;
        }
        jwt = authHeader.substring(7);
        username = jwtService.extractEmail(jwt);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            log.info("{}",userDetails.getAuthorities());
            if(jwtService.isTokenValid(jwt,userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                log.info("校验成功");
            }
        }
        filterChain.doFilter(request,response);

    }
}
