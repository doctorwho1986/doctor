package com.doctor.spring.context.annotationbasedconfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author doctor
 *
 * @since 2015年1月1日 上午1:29:24
 */
@Configuration
@ComponentScan(basePackages={"com.doctor.spring.context.annotationbasedconfiguration"})
public class AutowireAllBeansOfAParticularTypeConfig {

}
