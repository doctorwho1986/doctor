package com.doctor.mybatis3practice.manager.impl;

import static org.junit.Assert.*;

import java.time.Instant;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.doctor.mybatis3practice.domain.Product;
import com.doctor.mybatis3practice.manager.ProductManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:mybatisPractice-config/spring-context.xml" })
public class ProductManagerImplTest {

	@Resource
	private ProductManager productManager;
	
	private String tableName = "product";

	@Test
	public void test_CrateTable() {
		boolean crateTable = productManager.crateTable(tableName);
		assertTrue(crateTable);
	}

	
	@Test
	public void test_insertProduct(){
		Product product = new Product("who", "no desc", Instant.now());
		boolean insertProduct = productManager.insertProduct(tableName, product);
		assertTrue(insertProduct);
		System.out.println(product.getProductId());
	}
}
