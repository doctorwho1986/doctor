package com.doctor.mybatis3practice.manager.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.doctor.mybatis3practice.domain.Blog;
import com.doctor.mybatis3practice.manager.BlogManager;
import com.doctor.mybatis3practice.mapper.BlogMapper;

/**
 * 
 * @author doctor
 *
 * @since 2015年1月21日 下午10:03:15
 */
@Repository
public class BlogManagerImpl implements BlogManager {
	private static final Logger log = LoggerFactory.getLogger(BlogManagerImpl.class);
	
	@Resource
	private BlogMapper blogMapper;

	@Override
	public Blog queryBlog(Long id) {
		return blogMapper.queryById(id);
	}

	@Override
	public boolean insertBlog(Blog blog) {
		int count = blogMapper.insertBlog(blog);
		return count == 1 ? true : false;
	}

	@Override
	public boolean createNewTable(String tableName) {
		int count = blogMapper.createNewTable(tableName);
		return count == 1 ? true : false;
	}

	@Override
	public void dropTable(String tableName) {
		int dropTable = blogMapper.dropTable(tableName);
		log.info("{dropTable :'BlogManagerImpl {}'}",dropTable);
		
	}

	@Override
	public boolean existTable(String tableName) {
		int count = blogMapper.existTable(tableName);
		return count == 1 ? true : false;
	}

}
