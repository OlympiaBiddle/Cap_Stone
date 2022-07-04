package com.example.spring.security.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring.security.model.Contact;
import com.example.spring.security.model.User;
import com.example.spring.security.repository.ContactRepository;
import com.example.spring.security.repository.UserRepository;
import com.example.spring.security.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	   private UserService userService;
	
	@Autowired
	private ContactRepository contactRepository;
	
	
	//method for adding user model to response
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		//adding the User model to the dashboard
				String userName = principal.getName();
				System.out.println("USERNAME "+userName);
				
				User user= userService.findByEmail(userName);
				
				System.out.println("User "+user);
				
				model.addAttribute("user", user);
		
	}
	
	//dashboard
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {
		//
		model.addAttribute("title", "User Dashboard");
		return "normal/user_dashboard";
	}
	
	//open add contact form handler
	@GetMapping("/add-contact")
	public String openAddContactForm(Model model) {
		
		model.addAttribute("title", "Add Contact");
		model.addAttribute("contact", new Contact());
		return "normal/add_contact_form";
	}
	
	//save contact form
	//url must match the url from the post method
	@PostMapping("/process-contact")
	public String processContact(@ModelAttribute Contact contact, Principal principal)
	{
		String userName = principal.getName();
		User user = this.userService.findByEmail(userName);
		//user.getContacts().add(contact);
		
		Contact c1 = new Contact();
		c1.setName(contact.getName());
		c1.setEmail(contact.getEmail());
		c1.setPhone(contact.getPhone());
		c1.setDescription(contact.getDescription());
		c1.setImage(contact.getImage());
		c1.setWork(contact.getWork());
		c1.setUser(user);
		contactRepository.save(c1);
		
		
		
		System.out.println("DATA" +contact );
		
		System.out.println("Added to database");

		
		System.out.println("Data " +contact);
		return "normal/add_contact_form";
	}
}
