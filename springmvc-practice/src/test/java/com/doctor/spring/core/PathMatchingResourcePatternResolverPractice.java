package com.doctor.spring.core;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;
/**
 * 扫描包。。
 * @author doctor
 *
 * @time   2015年1月26日 上午11:19:50
 */

public class PathMatchingResourcePatternResolverPractice {

	public static void main(String[] args) throws IOException {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		String basePackage = ClassUtils.convertClassNameToResourcePath("com.doctor.spring.core");
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + basePackage + "/" + "**/*.class";
		Resource[] resources = resolver.getResources(packageSearchPath);
		Arrays.asList(resources).forEach(System.out::println);
	}

}
