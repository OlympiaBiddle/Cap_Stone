package com.olympiabiddle.keepintouch.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.olympiabiddle.keepintouch.dto.UserRegistrationDto;
import com.olympiabiddle.keepintouch.model.User;
import com.olympiabiddle.keepintouch.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

   @Autowired
   private UserService userService;

   @ModelAttribute("user")
   public UserRegistrationDto userRegistrationDto() {
	   log.info("Info log statement for Main Controller");
		log.warn("Warn log statement for Main Controller");
		log.error("Error log statement for Main Controller");
	   
	   
	   
       return new UserRegistrationDto();
   }

   @GetMapping
   public String showRegistrationForm(Model model) {
       return "registration";
   }

   @PostMapping
   public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result){

       User existing = userService.findByEmail(userDto.getEmail());
       if (existing != null){
           result.rejectValue("email", null, "There is already an account registered with that email");
       }

       if (result.hasErrors()){
           return "registration";
       }

       userService.save(userDto);
       return "redirect:/registration?success";
   }
}

