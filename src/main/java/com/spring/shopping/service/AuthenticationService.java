package com.spring.shopping.service;

import com.spring.shopping.controller.AuthenticateRequest;
import com.spring.shopping.controller.AuthenticationResponse;
import com.spring.shopping.controller.RegisterRequest;
import com.spring.shopping.entity.Role;
import com.spring.shopping.entity.User;
import com.spring.shopping.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        // default role : user
        final Role userRole = new Role(
                2, "user", null
        );
        var user = User.builder()
                .username(request.getUsername())
                .birthday(request.getBirthday())
                .password(passwordEncoder.encode(request.getPassword())) // save the crypted password
                .roleList(new ArrayList<>(Arrays.asList(userRole)))
                .build();
        // save the user into database
        userMapper.saveUser(user);
        // save the user's role into user_role

        User getNewUser = userMapper.selectUserByUsername(user.getUsername()).get();;

        userMapper.saveUserRole(getNewUser.getId(), userRole.getId());

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        System.out.println("222222222");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        System.out.println("-------------");
        var user = userMapper.selectUserByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        System.out.println("************");
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
