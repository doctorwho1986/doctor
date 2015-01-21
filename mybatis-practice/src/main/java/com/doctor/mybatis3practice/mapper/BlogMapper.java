package com.doctor.mybatis3practice.mapper;

import com.doctor.mybatis3practice.domain.Blog;

public interface BlogMapper {
	Blog queryById(Long id);

	int insertBlog(Blog blog);
}
