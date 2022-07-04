package com.example.spring.security.service;

import java.util.List;
import java.util.Optional;

import com.example.spring.security.model.Contact;

public interface ContactService {

	void saveContact(Contact contact);
	Iterable<Contact> getAllContacts();
	Contact getContactById(int id);
	void deleteContactById(int id);
}
