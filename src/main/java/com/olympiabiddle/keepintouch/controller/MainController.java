package com.olympiabiddle.keepintouch.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String root() {
    	log.info("Info log statement for Main Controller");
		log.warn("Warn log statement for Main Controller");
		log.error("Error log statement for Main Controller");
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    
	@GetMapping("/about")
	public String about(Model model)
	{
		return "about";
	}
}

