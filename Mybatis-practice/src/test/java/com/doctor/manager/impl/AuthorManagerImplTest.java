package com.doctor.manager.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.doctor.entity.Author;
import com.doctor.enums.FavouriteSection;
import com.doctor.manager.AuthorManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:mybatisPracticeConfig/spring-context.xml" })
public class AuthorManagerImplTest {

	@Autowired
	@Qualifier("authorManager")
	private AuthorManager authorManager;
	
	@Test
	public void testInsertAuthor() {
		Author author = new Author(null,"doctor","12367890","doctor@doctor.com","bio",FavouriteSection.NEWS);
		boolean b = authorManager.insertAuthor(author);
		assertTrue(b);
		assertNotNull(author.getId());
	}

	@Test
	public void testQueryById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAuthorById() {
		fail("Not yet implemented");
	}

}
