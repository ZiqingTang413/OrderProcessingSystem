package com.spring.shopping.service;

import com.spring.shopping.controller.AuthenticateRequest;
import com.spring.shopping.controller.AuthenticationResponse;
import com.spring.shopping.controller.RegisterRequest;
import com.spring.shopping.entity.Role;
import com.spring.shopping.entity.User;
import com.spring.shopping.mapper.UserMapper;
import com.spring.shopping.util.RedisCache;
import com.spring.shopping.util.ResponseRestful;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public ResponseRestful register(RegisterRequest request) {
        // default role : user
        final Role userRole = new Role(
                2, "user", null
        );
        // if username is already used
        if (userMapper.selectUserByUsername(request.getUsername()).isPresent()) {
            return new ResponseRestful(700,"Username already used ", null);
        }
        var user = User.builder()
                .username(request.getUsername())
                .birthday(request.getBirthday())
                .password(passwordEncoder.encode(request.getPassword())) // save the crypted password
                .roleList(new ArrayList<>(Arrays.asList(userRole.getRoleType())))
                .build();
        // save the user into database
        userMapper.saveUser(user);


        // save the user's role into user_role
        User getNewUser = userMapper.selectUserByUsername(user.getUsername()).get();

        userMapper.saveUserRole(getNewUser.getId(), userRole.getId());

        var jwtToken = jwtService.generateToken(user);
        // store user into Redis
        // store userDetails into Redis
        redisCache.setCacheObject("login" + getNewUser.getUsername(), getNewUser);
        /*return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();*/
        return new ResponseRestful(200, "register succeed", jwtToken);
    }
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    public ResponseRestful authenticate(AuthenticateRequest request) {

        // user authentication through authenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // authenticate failed
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("authenticate failed");
        }
        System.out.println("222222222");
        // get user by username through userMapper
        User user = (User)this.userDetailsService.loadUserByUsername(request.getUsername());
        System.out.println("333333333");
        // use userdetails generate token
        var jwtToken = jwtService.generateToken(user);

        System.out.println(user);

        // store userDetails into Redis
        redisCache.setCacheObject("login" + user.getUsername(), user);

        // key -user.username
        /*return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();*/
        return new ResponseRestful(200,"Authenticate succeed", jwtToken);
    }
}
