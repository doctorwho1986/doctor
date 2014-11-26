package com.doctor.manager.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.doctor.entity.Author;
import com.doctor.manager.AuthorManager;

@Repository("authorManager")
public class AuthorManagerImpl implements AuthorManager {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSessionTemplate sqlSessionTemplate;

	@Override
	public boolean insertAuthor(Author author) {
		if(sqlSessionTemplate.insert("AuthorDao.insertAuthor", author) == 1){
			return true;
		}
		return false;
	}

	@Override
	public Author queryById(Long id) {
		return sqlSessionTemplate.selectOne("AuthorDao.queryById", id);
	}

	@Override
	public boolean deleteAuthorById(Long id) {
		if(sqlSessionTemplate.delete("AuthorDao.deleteById", id) == 1){
			return true;
		}
		
		return false;
	}

}
