package com.spring.shopping.controller;

import com.spring.shopping.entity.User;
import com.spring.shopping.service.AuthenticationService;
import com.spring.shopping.util.ResponseRestful;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseRestful register(
            @RequestBody RegisterRequest request
    ){
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public ResponseRestful authenticate(
            @RequestBody AuthenticateRequest request
    ){
        return authenticationService.authenticate(request);
    }



}
