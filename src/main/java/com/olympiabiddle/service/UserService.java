package com.olympiabiddle.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.olympiabiddle.dto.UserRegistrationDto;
import com.olympiabiddle.model.Contact;
import com.olympiabiddle.model.User;

public interface UserService extends UserDetailsService {
   User findByEmail(String email);
   User save(UserRegistrationDto registration);
  
   
}

