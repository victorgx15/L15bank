package com.victor.antoine.L15.L15bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class L15bankApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(L15bankApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(L15bankApplication.class);
    }

}

