package com.spring.shopping.service;

import com.spring.shopping.entity.User;
import com.spring.shopping.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectUserRoleByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        //User user = opt.get();
        // get role of the user from database
        return userMapper.selectUserRoleByUsername(user.getUsername());



    }
}
