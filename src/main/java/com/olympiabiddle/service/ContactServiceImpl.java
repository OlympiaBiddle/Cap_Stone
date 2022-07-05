package com.olympiabiddle.service;


import java.util.Optional;


import org.springframework.stereotype.Service;

import com.olympiabiddle.model.Contact;
import com.olympiabiddle.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	
	
	private ContactRepository contactRepository;
	

	public ContactServiceImpl(ContactRepository contactRepository) {
		
		this.contactRepository = contactRepository;
	}

	@Override
	public void saveContact(Contact contact) {
		// TODO Auto-generated method stub
//		String userName = principal.getName();
//		User user = this.userService.findByEmail(userName);
//		
//		Contact c1 = new Contact();
//		c1.setName(contact.getName());
//		c1.setEmail(contact.getEmail());
//		c1.setPhone(contact.getPhone());
//		c1.setDescription(contact.getDescription());
//		c1.setImage(contact.getImage());
//		c1.setWork(contact.getWork());
		
		this.contactRepository.save(contact);
		
	}

	@Override
	public Iterable<Contact> getAllContacts() {
		// TODO Auto-generated method stub
		return contactRepository.findAll();
	}

	@Override
	public Contact getContactById(int id) {
		// TODO Auto-generated method stub
		Optional<Contact> optional = contactRepository.findById(id);
		Contact contact = null;
		if (optional.isPresent()) {
			contact = optional.get();
		} else {
			throw new RuntimeException("Contact with contact id: " + id + "not found");
		}
		return contact;
	}

	@Override
	public void deleteContactById(int id) {
		// TODO Auto-generated method stub
		this.contactRepository.deleteById(id);;
	}
	
	
	

}
