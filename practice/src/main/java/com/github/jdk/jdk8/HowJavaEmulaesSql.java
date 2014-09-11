package com.github.jdk.jdk8;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author see
 *         http://technology.amis.nl/2013/10/05/java-8-collection-enhancements
 *         -leveraging-lambda-expressions-or-how-java-emulates-sql/
 *
 */
public class HowJavaEmulaesSql {

	public static void main(String[] args) {
		List<PersonT> list = new ArrayList<>();

	}

}



class PersonT{
	public enum Gender{
		MALE,FEMALE
	}
	
	private String firstName;
	private String lastName;
	private String city;
	private Integer salary;
	private LocalDateTime dateOfBirth;
	private Gender gender;
	
	public PersonT(String firstName,String lastName,String city,Integer salary,LocalDateTime dateOfBirth,Gender gender){
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.salary = salary;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public LocalDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		LocalDateTime now = LocalDateTime.now();
		long year = ChronoUnit.YEARS.between(now, this.dateOfBirth);
		
		return Long.valueOf(year).intValue();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
}