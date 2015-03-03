package com.doctor.spring4.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源配置
 * 数据源配置个人觉得还是xml好些。用xml配置改动增加配置只需重启
 * 
 * @author doctor
 *
 * @time 2015年3月3日 下午2:57:10
 */
@Configuration
@Import({ DataSourceConfig.MybatisGreenplumConfig.class })
public class DataSourceConfig {

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface DbGreenplum {

	}

	/**
	 * Greenplum 数据原配置
	 * 
	 * @author doctor
	 *
	 * @time 2015年3月3日 下午3:26:15
	 */
	@Configuration
	@MapperScan(basePackages = { "com.doctor.spring4.common.mapper" }, annotationClass = DbGreenplum.class, sqlSessionFactoryRef = "DbGreenplumSqlSessionFactory")
	@PropertySource("classpath:/spring4_2015Pro/jdbc-greenplum.properties")
	public static class MybatisGreenplumConfig {

		@Value("${jdbc.greenplum.url}")
		private String url;

		@Value("${jdbc.greenplum.user}")
		private String user;

		@Value("${jdbc.greenplum.password}")
		private String password;

		@Value("${jdbc.greenplum.driverClassName}")
		private String driverClassName;

		@Value("${jdbc.greenplum.initialSize}")
		private int initialSize;

		@Value("${jdbc.greenplum.minIdle}")
		private int minIdle;

		@Value("${jdbc.greenplum.maxActive}")
		private int maxActive;

		@Value("${jdbc.greenplum.maxWait}")
		private long maxWait;

		@Value("${jdbc.greenplum.minEvictableIdleTimeMillis}")
		private long minEvictableIdleTimeMillis;

		@Value("${jdbc.greenplum.timeBetweenEvictionRunsMillis}")
		private long timeBetweenEvictionRunsMillis;

		@Value("${jdbc.greenplum.validationQuery}")
		private String validationQuery;

		@Value("${jdbc.greenplum.testWhileIdle}")
		private boolean testWhileIdle;

		@Value("${jdbc.greenplum.testOnBorrow}")
		private boolean testOnBorrow;

		@Value("${jdbc.greenplum.testOnReturn}")
		private boolean testOnReturn;

		@Value("${jdbc.greenplum.poolPreparedStatements}")
		private boolean poolPreparedStatements;

		@Value("${jdbc.greenplum.maxPoolPreparedStatementPerConnectionSize}")
		private int maxPoolPreparedStatementPerConnectionSize;

		@Bean(name = "DbGreenplumDataSource", initMethod = "init", destroyMethod = "close")
		public DataSource DbGreenplumDataSource() {
			DruidDataSource druidDataSource = new DruidDataSource();
			druidDataSource.setUrl(url);
			druidDataSource.setUsername(user);
			druidDataSource.setPassword(password);
			
			druidDataSource.setDriverClassName(driverClassName);
			
			druidDataSource.setInitialSize(initialSize);
			druidDataSource.setMinIdle(minIdle);
			druidDataSource.setMaxActive(maxActive);
			druidDataSource.setMaxWait(maxWait);
			
			druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
			druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
			
			druidDataSource.setValidationQuery(validationQuery);
			druidDataSource.setTestWhileIdle(testWhileIdle);
			druidDataSource.setTestOnBorrow(testOnBorrow);
			druidDataSource.setTestOnReturn(testOnReturn);
			
			druidDataSource.setPoolPreparedStatements(poolPreparedStatements);
			druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
			return druidDataSource;
		}

		@Bean(name = "DbGreenplumSqlSessionFactory")
		@Resource(name = "DbGreenplumDataSource")
		public SqlSessionFactory DbGreenplumSqlSessionFactory(DataSource dataSource) throws Exception {
			SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

			sqlSessionFactoryBean.setDataSource(dataSource);
			sqlSessionFactoryBean.setTypeHandlersPackage("");

			sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("/spring4_2015Config/mybatis-db-config.xml"));
			return sqlSessionFactoryBean.getObject();
		}

	}
}
