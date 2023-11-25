package com.spring.shopping.controller;

import com.spring.shopping.service.LogoutService;
import com.spring.shopping.util.ResponseRestful;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogoutController {
    @Autowired
    LogoutService logoutService;
    @GetMapping("/api/v1/logout")
    public ResponseRestful logout() {
        return logoutService.logout();
    }


}
