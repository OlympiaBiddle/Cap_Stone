package com.olympiabiddle.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.olympiabiddle.Application;
import com.olympiabiddle.controller.MainController;
import com.olympiabiddle.controller.UserController;
import com.olympiabiddle.controller.UserRegistrationController;
import com.olympiabiddle.implementation.UserServiceImpl;
import com.olympiabiddle.model.Contact;
import com.olympiabiddle.service.ContactServiceImpl;

@SpringBootTest(classes= Application.class)
class ApplicationTests {

	private MainController mController;
	private UserController userController;
	private UserRegistrationController urController;
	private ContactServiceImpl contactserviceSvc;
	private UserServiceImpl uService;

	@Autowired
	public ApplicationTests(MainController mController, UserController userController,
			UserRegistrationController urController, ContactServiceImpl contactserviceSvc, UserServiceImpl uService) {

		this.mController = mController;
		this.userController = userController;
		this.urController = urController;
		this.contactserviceSvc = contactserviceSvc;
		this.uService = uService;
	}

	@Test
	void contextLoads() {
		
		assertNotNull(uService);
		assertNotNull(urController);
		assertNotNull(mController);
		assertNotNull(contactserviceSvc);
		assertNotNull(userController);
	}
	
	@Test
	void testUserServiceCurrentService() {
		UserDetails us = uService.loadUserByUsername("mikeJones@yahoo.com");
		assertNotNull(us);
		assertTrue(us instanceof UserDetails);
		assertEquals("mikeJones@yahoo.com", us.getUsername());
	}
	
	@Test
	void getContactById() {
		Contact details = contactserviceSvc.getContactById(12);
		assertNotNull(details);
		assertTrue(details instanceof Contact);
		assertEquals(12, details.getcId());
	}
	
	@Test
	void getAllContacts() {
		Iterable<Contact> contacts = contactserviceSvc.getAllContacts();
		assertNotNull(contacts);
	}
}
