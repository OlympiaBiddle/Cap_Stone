package com.example.spring.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.spring.security.model.Contact;
import com.example.spring.security.model.User;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
	
Contact save(Contact contact);
}
