package com.doctor.spring4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * root spring 配置
 * @author doctor
 *
 * @time 2015年3月2日 下午2:58:40
 */
@Configuration
@ComponentScan(basePackages = { "com.doctor.spring4.manager" })
@Import({ DataSourceConfig.class })
@ImportResource({"classpath:/spring4_2015Config/springweb-filter.xml"})
public class RootConfig {

}
