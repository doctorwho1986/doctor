package com.doctor.mybatis3practice.manager;

import com.doctor.mybatis3practice.domain.Product;

public interface ProductManager {
	boolean crateTable(String tableName);
	boolean insertProduct(String tableName,Product product);
	Product queryById(String tableName,Long id);
}
