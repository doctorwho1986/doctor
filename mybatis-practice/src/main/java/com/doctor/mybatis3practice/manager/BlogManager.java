package com.doctor.mybatis3practice.manager;

import com.doctor.mybatis3practice.domain.Blog;
/**
 * 
 * @author doctor
 *
 * @since 2015年1月21日 下午10:02:57
 */
public interface BlogManager {
	Blog queryBlog(Long id);
	boolean insertBlog(Blog blog);
}
