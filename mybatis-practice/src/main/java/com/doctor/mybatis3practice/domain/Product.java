package com.doctor.mybatis3practice.domain;

import java.io.Serializable;
import java.time.Instant;

import org.apache.ibatis.type.Alias;

import com.alibaba.fastjson.JSON;

@Alias("Product")
public class Product implements Serializable {
	 
	private static final long serialVersionUID = -2595412185619389281L;
	private Long productId;
	private String name;
	private String description;
	private Instant createTime;

	public Product() {

	}

	public Product(String name, String description, Instant createTime) {
		this.name = name;
		this.description = description;
		this.createTime = createTime;
	}

	public Product(Long productId,String name, String description, Instant createTime){
		this(name,description,createTime);
		this.productId = productId;
	}
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Instant createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
