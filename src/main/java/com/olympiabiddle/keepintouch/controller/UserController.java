package com.olympiabiddle.keepintouch.controller;


import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.olympiabiddle.keepintouch.helper.Message;
import com.olympiabiddle.keepintouch.model.Contact;
import com.olympiabiddle.keepintouch.model.User;
import com.olympiabiddle.keepintouch.repository.ContactRepository;
import com.olympiabiddle.keepintouch.service.ContactService;
import com.olympiabiddle.keepintouch.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	   private UserService userService;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private ContactService contactService;
	
	
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
		log.info("Info log statement for Main Controller");
		log.warn("Warn log statement for Main Controller");
		log.error("Error log statement for Main Controller");
		
		
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
	public String processContact(@ModelAttribute Contact contact,
			Principal principal, HttpSession session)
	{
		
		try {
		String userName = principal.getName();
		User user = this.userService.findByEmail(userName);
		//user.getContacts().add(contact);
		
		Contact c1 = new Contact();
		c1.setName(contact.getName());
		c1.setEmail(contact.getEmail());
		c1.setPhone(contact.getPhone());
		c1.setDescription(contact.getDescription());
		c1.setWork(contact.getWork());
		c1.setUser(user);
		contactService.saveContact(c1);
		
		session.setAttribute("message", new Message("Your Contact was added! You can add more.", "success"));
		
		/*
		 * test to see if it was working correctly
		 
		System.out.println("DATA" +contact );
		
		System.out.println("Added to database");

		
		System.out.println("Data " +contact);
		*/
		
		//Print success message
		
		}catch (Exception e) {
			System.out.println("Error "+ e.getMessage());
			e.printStackTrace();
			//print error message
			session.setAttribute("message", new Message("Something went wrong! Try it again!", "danger"));
			
		}
		
		
		return "normal/add_contact_form";
	}
	
	
	//show contacts handler
	//will show only 5 contacts per page=5[n]
	//current page = 0[page]
	@GetMapping("/show-contacts/{page}")
	public String showContacts(@PathVariable("page") Integer page,
			Model m, Principal principal) {
		
		m.addAttribute("title", "Show User Contacts");
		//show list of contacts
		
		String userName = principal.getName();
		User user = this.userService.findByEmail(userName);
		
		
		//currentPage-page
		//Contact Per page - 5
		Pageable pageable = PageRequest.of(page, 6);
		
		
		Page<Contact> contacts = this.contactRepository.findContactsByUser(user.getId(),pageable);
		m.addAttribute("contacts", contacts);
		m.addAttribute("currentPage", page);
		
		
		m.addAttribute("totalPages", contacts.getTotalPages());
		return "normal/show_contacts";
		
	}
	
	//show individual contact detail
	@RequestMapping("/{cId}/contact")
	public String showContactDetail(@PathVariable("cId") Integer cId,
			Model model, Principal principal) {
		
		String userName = principal.getName();
		User user = this.userService.findByEmail(userName);
		
		System.out.println("cId" +cId);
		
		Contact contact = contactService.getContactById(cId);
		
		System.out.println(contact);
		
		if(user.getId() == contact.getUser().getId())
		{
			model.addAttribute("contact", contact);
			model.addAttribute("title", contact.getName());
		}
		
		//model.addAttribute("contact", contact);
		
		return "normal/contact_detail";
	}
	
	
	//delete contact by Id
	@GetMapping("/delete/{cid}")
	public String deleteContact(@PathVariable("cid") Integer cId,
			Model model,
			HttpSession session) {
		
		//check..assignment
		 this.contactRepository.deleteByIdCustom(cId);
		
		
		session.setAttribute("message", new Message("Contacted deleted successfully",
				"success"));
		
		return "redirect:/user/show-contacts/0";
	}
	
	//open update Contact by id
	@PostMapping("/update-contact/{cid}")
	public String updateForm(@PathVariable("cid") Integer cId,
			Model m) {
		
		m.addAttribute("title", "Update Contact");
		
		
		Contact contact = contactService.getContactById(cId);
		
		m.addAttribute("contact", contact);
		
		return "normal/update_form";
	}
	
	//update contact by id
	@RequestMapping(value="/process-update", method= RequestMethod.POST)
	public String updateContact(@ModelAttribute Contact contact,
			Principal principal) {
		
		
		String userName = principal.getName();
		User user = this.userService.findByEmail(userName);
		contact.setUser(user);
		this.contactRepository.save(contact);
		
		//print statements to see if they are working
		System.out.println("Contact Name "+contact.getName());
		System.out.println("Contact ID: "+contact.getcId());
		
		return "redirect:/user/show-contacts/0";
		//return "redirect:/user/"+contact.getcId()+"/contact";
	}
	
	//user profile
	@GetMapping("/profile")
	public String userProfile(Model model) {
		model.addAttribute("title", "Profile Page");
		return "normal/profile";
	}
	
}
