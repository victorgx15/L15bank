package com.L15user.L15user;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/accounts_overview").setViewName("accounts_overview");
        registry.addViewController("/account").setViewName("account");
        registry.addViewController("/usr_accounts").setViewName("usr_accounts");
        registry.addViewController("/operation_search").setViewName("operation_search");
    }
	
	
}