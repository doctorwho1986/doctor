package com.doctor.spring.core;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.Resource;

import com.google.common.base.Preconditions;

/**
 * @see ClassPathResource#resolveURL
 *      区别this.clazz.getResource(this.path);this.classLoader.getResource(this.path)
 * 
 * @author doctor
 *
 * @time 2015年1月23日 下午4:56:37
 */
public class ClassPathResourcePractice {

	public static void main(String[] args) throws IOException {
		String fileName = "logback-test.xml";

		ClassPathResource classPathResource = new ClassPathResource(fileName);
		Preconditions.checkState(classPathResource.exists());

		Preconditions.checkState(fileName.equals(classPathResource.getPath()));
		System.out.println(classPathResource.getPath());

		System.out.println(classPathResource.getFile());

		ClassRelativeResourceLoader resourceLoader = new ClassRelativeResourceLoader(ClassPathResourcePractice.class);
		System.out.println(resourceLoader.getClassLoader());

		String fileProtocol = "classpath:";
		Resource resource = resourceLoader.getResource(fileProtocol + fileName);
		System.out.println(resource.getFile());
		Preconditions.checkState(resource.exists());
	}

}
