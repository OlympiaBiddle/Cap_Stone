package com.example.spring.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.spring.security.model.Contact;
import com.example.spring.security.model.User;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
	
Contact save(Contact contact);
//pagination

@Query("from Contact as c where c.user.id =:userId")
public List<Contact> findContactsByUser(@Param("userId")long userId);

}
