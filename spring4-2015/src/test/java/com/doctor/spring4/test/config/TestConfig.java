package com.doctor.spring4.test.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.doctor.spring4.config.RootConfig;

@ContextConfiguration
@Import({RootConfig.class})
public class TestConfig {

	@Bean(name = "dbh2JdbcTemplate")
	@Resource(name="dbH2DataSource")
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
}
