package com.olympiabiddle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.olympiabiddle.model.User;

//import net.perscholas.springboot.springsecurity.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
  
}

