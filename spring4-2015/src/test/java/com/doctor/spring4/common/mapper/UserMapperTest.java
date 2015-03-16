package com.doctor.spring4.common.mapper;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.doctor.spring4.common.domain.User;
import com.doctor.spring4.test.config.TestConfig;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class UserMapperTest {

	@Resource
	private UserMapper userMapper;

	@Resource(name = "dbh2JdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	private String tableName = "spring4_2015_user";
	private String table = "\"" + tableName + "\"";

	@Test
	public void testCreateUserTable() {
		
		jdbcTemplate.execute("drop table if exists " + table );
		userMapper.createUserTable(tableName);
		Integer count  = jdbcTemplate.queryForObject("select count(1)  from information_schema.tables t where t.table_name = '" + tableName + "\'", Integer.class); 
		assertThat(count, equalTo(1));
	}
	
	@Test
	public void test_existTable_no(){
		jdbcTemplate.execute("drop table if exists " + table );
		Integer count  = jdbcTemplate.queryForObject("select count(1)  from information_schema.tables t where t.table_name = '" + tableName + "\'", Integer.class); 
		assertThat(count, equalTo(0));
	}
	
	@Test
	public void test_existTable_yes(){
		Integer count  = jdbcTemplate.queryForObject("select count(1)  from information_schema.tables t where t.table_name = 'TABLES'", Integer.class); 
		int num = userMapper.existTable("TABLES");
		assertThat(count, equalTo(num));
		assertThat(num, equalTo(1));
	}
	
	@Test
	public void test_insertAll(){
		List<User> users = Lists.newArrayList(new User(1L, "doctor", "doctor", "xxx", "xx", "ss@foxm", "s", 2L),new User(2L, "doctorw", "doctorw", "xxxx", "xxx", "xss@foxm", "xs", 2L));
		int count = userMapper.insertAll(users);
		assertThat(count, equalTo(2));
	}

}
