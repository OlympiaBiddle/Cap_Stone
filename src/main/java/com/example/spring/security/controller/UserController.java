package com.example.spring.security.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring.security.model.User;
import com.example.spring.security.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	   private UserService userService;
	
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {
		
		//adding the User model to the dashboard
		String userName = principal.getName();
		System.out.println("USERNAME "+userName);
		
		User user= userService.findByEmail(userName);
		
		System.out.println("User "+user);
		
		model.addAttribute("user", user);
		return "normal/user_dashboard";
	}

}
