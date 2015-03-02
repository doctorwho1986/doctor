package com.doctor.spring4.common.domain;

import java.time.LocalDateTime;

import com.google.common.base.MoreObjects;
/**
 * 
 * @author doctor
 *
 * @since 2015年3月3日 上午00:02:51
 */
public class Contact {
	private Long id;
	private String firstName;
	private String lastName;
	private LocalDateTime birth;

	public Contact() {

	}

	public Contact(Long id, String firstName, String lastName, LocalDateTime birth) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birth = birth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getBirth() {
		return birth;
	}

	public void setBirth(LocalDateTime birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("firstName", firstName)
				.add("lastName", lastName)
				.add("birth", birth)
				.toString();
	}

}
