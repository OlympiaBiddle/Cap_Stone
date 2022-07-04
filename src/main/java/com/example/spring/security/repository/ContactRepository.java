package com.example.spring.security.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.security.model.Contact;
import com.example.spring.security.model.User;

public interface ContactRepository extends CrudRepository<Contact, Integer> {
	
Contact save(Contact contact);
//pagination

//currentPage-page
//Contact Per page - 5
@Query("from Contact as c where c.user.id =:userId")
public Page<Contact> findContactsByUser(@Param("userId")long userId, Pageable pePageable);

@Modifying
@Transactional
@Query(value="delete from Contact c where c.cId = ?1")
void deleteByIdCustom(Integer cId);

}
