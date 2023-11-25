package com.spring.shopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAuthority {

    @GetMapping("/admin_only")
    @PreAuthorize("hasAuthority('test')")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from a secured endpoint");
    }

}
