package com.L15user.L15user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.L15user.L15user")
public class L15userApplication {

	public static void main(String[] args) {
		SpringApplication.run(L15userApplication.class, args);
	}

}

