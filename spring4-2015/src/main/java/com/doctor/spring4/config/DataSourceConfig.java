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
public class DataSourceConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	public @interface DbH2 {

	}

	/**
	 * H2 数据原配置
	 * 不用import ,原因文档见：{@code Configuration}文档中的With nested 　Configuration　 classes部分
	 * spring源码如何处理见：
	 * {@link org.springframework.context.annotation.ConfigurationClassParser#processMemberClasses(ConfigurationClass, SourceClass) }
	 * 
	 * @author doctor
	 *
	 * @time 2015年3月3日 下午3:26:15
	 */
	@Configuration
	@PropertySource("classpath:/spring4_2015Pro/jdbc-H2.properties")
	static class MybatisH2Config {

		@Value("${jdbc.H2.url}")
		private String url;

		@Value("${jdbc.H2.user}")
		private String user;

		@Value("${jdbc.H2.password}")
		private String password;

		@Value("${jdbc.H2.driverClassName}")
		private String driverClassName;

		@Value("${jdbc.H2.initialSize}")
		private int initialSize;

		@Value("${jdbc.H2.minIdle}")
		private int minIdle;

		@Value("${jdbc.H2.maxActive}")
		private int maxActive;

		@Value("${jdbc.H2.maxWait}")
		private long maxWait;

		@Value("${jdbc.H2.minEvictableIdleTimeMillis}")
		private long minEvictableIdleTimeMillis;

		@Value("${jdbc.H2.timeBetweenEvictionRunsMillis}")
		private long timeBetweenEvictionRunsMillis;

		@Value("${jdbc.H2.validationQuery}")
		private String validationQuery;

		@Value("${jdbc.H2.testWhileIdle}")
		private boolean testWhileIdle;

		@Value("${jdbc.H2.testOnBorrow}")
		private boolean testOnBorrow;

		@Value("${jdbc.H2.testOnReturn}")
		private boolean testOnReturn;

		@Value("${jdbc.H2.poolPreparedStatements}")
		private boolean poolPreparedStatements;

		@Value("${jdbc.H2.maxPoolPreparedStatementPerConnectionSize}")
		private int maxPoolPreparedStatementPerConnectionSize;

		@Bean(name = "dbH2DataSource", initMethod = "init", destroyMethod = "close")
		public DataSource dbH2DataSource() {
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

		@Bean(name = "dbH2SqlSessionFactory")
		@Resource(name = "dbH2DataSource")
		public SqlSessionFactory DbH2SqlSessionFactory(DataSource dataSource) throws Exception {
			SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

			sqlSessionFactoryBean.setDataSource(dataSource);
			sqlSessionFactoryBean.setTypeHandlersPackage("");

			sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("/spring4_2015Config/mybatis-db-config.xml"));
			return sqlSessionFactoryBean.getObject();
		}

	}

	@Configuration
	@MapperScan(basePackages = { "com.doctor.spring4.common.mapper" }, annotationClass = DbH2.class, sqlSessionFactoryRef = "dbH2SqlSessionFactory")
	static class MyBatisMapperConfig {
		// MapperScan注解不要和数据源定义的配置写在一起，（如MybatisH2Config配置上），
		// 否此会导致循环引用初始化bean问题．
		// 看来xml配置还是有优势的

		// 03-11 17:01:50.010 main WARN o.s.b.f.s.DefaultListableBeanFactory - Bean creation
		// exception on FactoryBean type check:
		// org.springframework.beans.factory.BeanCreationException: Error creating bean with name
		// 'userMapper' defined in file
		// [/home/cui/workspace/spring4-2015/target/classes/com/doctor/spring4/common/mapper/UserMapper.class]:
		// Cannot resolve reference to bean 'dbH2SqlSessionFactory' while setting bean property
		// 'sqlSessionFactory'; nested exception is
		// org.springframework.beans.factory.BeanCurrentlyInCreationException: Error creating bean
		// with name 'dbH2SqlSessionFactory': Requested bean is currently in creation: Is there an
		// unresolvable circular reference?

	}
}
