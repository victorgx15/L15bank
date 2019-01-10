package com.victor.antoine.L15.L15bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Greeting {
	
	@RequestMapping("/hello")
	public String greeting() {
        return "greeting";
    }
}
