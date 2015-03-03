package com.doctor.spring4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doctor.spring4.manager.ContactManager;

/**
 * 
 * @author doctor
 *
 * @since 2015年3月3日 上午00:32:49
 */
@Controller
public class ContactController {
	
	@Autowired
	private ContactManager contactManager;

	@RequestMapping({"/contacts.json","/contacts.html"})
	public String contacts(Model model) {
		model.addAttribute("contacts", contactManager.getContacts());
		return "contacts";
	}
}
