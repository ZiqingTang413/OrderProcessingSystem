package com.spring.shopping.service;

import com.spring.shopping.entity.User;
import com.spring.shopping.util.RedisCache;
import com.spring.shopping.util.ResponseRestful;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService {
    @Autowired
    private RedisCache redisCache;
    public ResponseRestful logout() {
        // get username from contextholder
        Authentication authen = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authen.getPrincipal();

        // delete user from Redis
        redisCache.deleteObject("login" + user.getUsername());

        return new ResponseRestful(200, "logout succeed", null);
    }

}
