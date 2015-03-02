package com.doctor.spring4.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.doctor.spring4.manager"})
public class RootConfig {

}
