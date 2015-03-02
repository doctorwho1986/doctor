package com.doctor.spring4.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doctor.spring4.common.domain.Contact;

/**
 * @author doctor
 *
 * @since 2015年3月3日 上午00:06:40
 */
@Repository
public class ContactManager {

	private static final List<Contact> contacts = new ArrayList<>();
	static {
		contacts.add(new Contact(1L, "doctor", "who", LocalDateTime.of(1900, 1, 1, 1, 1)));
		contacts.add(new Contact(2L, "Jane", "who", LocalDateTime.of(1901, 1, 1, 1, 1)));
		contacts.add(new Contact(3L, "John", "who", LocalDateTime.of(1910, 1, 1, 1, 1)));

	}

	public List<Contact> getContacts() {
		return contacts;
	}
}
