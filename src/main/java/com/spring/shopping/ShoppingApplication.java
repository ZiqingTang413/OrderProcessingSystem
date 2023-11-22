package com.spring.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.spring.shopping.mapper")
public class ShoppingApplication {

	public static void main(String[] args) {

		SpringApplication.run(ShoppingApplication.class, args);

	}

}
