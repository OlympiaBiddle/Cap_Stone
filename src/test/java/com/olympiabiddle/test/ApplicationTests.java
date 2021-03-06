package com.olympiabiddle.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.olympiabiddle.keepintouch.Application;
import com.olympiabiddle.keepintouch.controller.MainController;
import com.olympiabiddle.keepintouch.controller.UserController;
import com.olympiabiddle.keepintouch.controller.UserRegistrationController;
import com.olympiabiddle.keepintouch.implementation.UserServiceImpl;
import com.olympiabiddle.keepintouch.model.Contact;
import com.olympiabiddle.keepintouch.service.ContactServiceImpl;

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
