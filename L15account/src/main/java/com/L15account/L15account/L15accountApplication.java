package com.L15account.L15account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.L15account.L15account")
public class L15accountApplication {

	public static void main(String[] args) {
		SpringApplication.run(L15accountApplication.class, args);
	}

}