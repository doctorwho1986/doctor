package com.doctor.mybatis3practice.manager.impl;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.doctor.mybatis3practice.domain.Blog;
import com.doctor.mybatis3practice.manager.BlogManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:mybatisPractice-config/spring-context.xml" })
public class BlogManagerImplTest {

	@Resource
	private BlogManager blogManager;

	@Test
	public void testQueryBlog() {
		Blog blog = new Blog();
		blog.setAuthorId(11L);
		blog.setTitle("who know");

		boolean b = blogManager.insertBlog(blog);
		assertTrue(b);

		Blog blogFromDb = blogManager.queryBlog(blog.getId());
		assertNotNull(blog);
		assertThat(blog.toString(), equalTo(blogFromDb.toString()));
	}

	@Test
	public void testInsertBlog() {
		Blog blog = new Blog();
		blog.setAuthorId(11L);
		blog.setTitle("who know");

		boolean b = blogManager.insertBlog(blog);
		assertTrue(b);
	}

	@Test
	public void test_existTable() {
		String tableName = "blog1";
		boolean existTable = blogManager.existTable(tableName);
		System.out.println("existTable " + tableName + ": " + existTable);
	}

	@Test
	public void test_dropTable() {
		String tableName = "blog1";
		blogManager.dropTable(tableName);

	}

	@Test
	public void test_createNewTable() {
		String tableName = "blog1";
		boolean b = blogManager.createNewTable(tableName);
		System.out.println(b);
	}
}
