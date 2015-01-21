package com.doctor.mybatis3practice.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author doctor
 *
 * @since 2015年1月21日 下午9:24:32
 */
@Alias("Blog")
public class Blog implements Serializable{
	private static final long serialVersionUID = 7877448657680553439L;
	
	private Long id;
	private Long authorId;
	private String title;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	
}
