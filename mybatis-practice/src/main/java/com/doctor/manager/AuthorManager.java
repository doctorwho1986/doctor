package com.doctor.manager;

import com.doctor.entity.Author;

public interface AuthorManager {
	public boolean insertAuthor(Author author);
	public Author queryById(Long id);
	public boolean deleteAuthorById(Long id);
	
	
}
