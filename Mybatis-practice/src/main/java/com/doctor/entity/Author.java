package com.doctor.entity;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.doctor.enums.FavouriteSection;

@Alias("Author")
public class Author implements Serializable{
	private static final long serialVersionUID = -2844004896156574615L;
	
	private Long id;
	private String username;
	private String password;
	private String email;
	private String bio;
	private FavouriteSection favouriteSection;
	
	public Author(){}
	public Author(Long id,String username,String password,String email,String bio,FavouriteSection favouriteSection){
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.bio = bio;
		this.favouriteSection = favouriteSection;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public FavouriteSection getFavouriteSection() {
		return favouriteSection;
	}
	public void setFavouriteSection(FavouriteSection favouriteSection) {
		this.favouriteSection = favouriteSection;
	}
	
	 
}

