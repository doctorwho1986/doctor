package com.doctor.mybatis3practice.mapper;

import org.apache.ibatis.annotations.Param;

import com.doctor.mybatis3practice.domain.Blog;

public interface BlogMapper {
	Blog queryById(Long id);

	int insertBlog(Blog blog);
	int createNewTable(@Param("tableName") String tableName);
	int dropTable(@Param("tableName") String tableName);
	int existTable(String tableName);
}
