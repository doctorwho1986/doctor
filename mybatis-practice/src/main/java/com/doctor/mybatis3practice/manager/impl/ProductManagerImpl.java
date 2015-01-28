package com.doctor.mybatis3practice.manager.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.doctor.mybatis3practice.domain.Product;
import com.doctor.mybatis3practice.manager.ProductManager;
import com.doctor.mybatis3practice.mapper.ProductMapper;
import com.google.common.base.Preconditions;

@Repository
public class ProductManagerImpl implements ProductManager {

	@Resource
	private ProductMapper productMapper;

	@Override
	public boolean crateTable(String tableName) {
		if (productMapper.existTable(tableName) == 1) {
			productMapper.dropTable(tableName);
		}
		
		productMapper.createTable(tableName);
		int count = productMapper.existTable(tableName);
		return 1==count ? true:false;
	}

	@Override
	public boolean insertProduct(String tableName, Product product) {
		Preconditions.checkArgument(StringUtils.isNotBlank(tableName));
		Preconditions.checkNotNull(product);
		
		int count = productMapper.insertProduct(tableName, product);
		return 1==count ? true:false;
	}

}
