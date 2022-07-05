package com.olympiabiddle.keepintouch.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.olympiabiddle.keepintouch.dto.UserRegistrationDto;
import com.olympiabiddle.keepintouch.model.Contact;
import com.olympiabiddle.keepintouch.model.User;

public interface UserService extends UserDetailsService {
   User findByEmail(String email);
   User save(UserRegistrationDto registration);
  
   
}

