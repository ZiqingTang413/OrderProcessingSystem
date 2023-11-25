package com.spring.shopping;

import com.spring.shopping.configuration.ApplicationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@SpringBootTest
public class PasswordEncoderTest {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void generateEncryptedPassword() {

        System.out.println(passwordEncoder.encode("123"));
        System.out.println(passwordEncoder.encode("234"));
        System.out.println(passwordEncoder.encode("789"));
        System.out.println(passwordEncoder.encode("010"));
        System.out.println(passwordEncoder.encode("202"));

    }
}
