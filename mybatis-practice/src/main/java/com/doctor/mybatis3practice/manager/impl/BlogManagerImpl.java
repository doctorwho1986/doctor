package com.doctor.mybatis3practice.manager.impl;

import javax.annotation.Resource;

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

}
