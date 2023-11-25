package com.spring.shopping.configuration;

import com.spring.shopping.entity.User;
import com.spring.shopping.service.JwtService;
import com.spring.shopping.service.UserDetailsServiceImpl;
import com.spring.shopping.util.RedisCache;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request
            , @NonNull HttpServletResponse response
            , @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7); // right after "Bearer "
        // extract username from token
        userName = jwtService.extractUsername(jwtToken);
        // username exists but has not Authenticated(is logged in)
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // load user from Redis
            User user = redisCache.getCacheObject("login"+userName);
            if (Objects.isNull(user)) {
                throw new RuntimeException("User has not logged in");
            }
            // if token has not expired
            if(!jwtService.isTokenExpired(jwtToken)) {
                // store user into SecurityContextHolder
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                // optional
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
        filterChain.doFilter(request, response);



    }
}
