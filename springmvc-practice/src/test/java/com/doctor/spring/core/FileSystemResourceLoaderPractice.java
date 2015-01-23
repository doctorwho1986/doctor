package com.doctor.spring.core;

import java.io.IOException;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;

import com.google.common.base.Preconditions;

public class FileSystemResourceLoaderPractice {

	public static void main(String[] args) throws IOException {
		String fileName = "logback-test.xml";
		String fileProtocol = "file:";
		String path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
		System.out.println(path);
		
		FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
		Preconditions.checkState(FileSystemResourceLoader.class.equals(resourceLoader.getClass()));
		System.out.println(resourceLoader.getClass());
		
		System.out.println(resourceLoader.getClassLoader());//sun.misc.Launcher$AppClassLoader@73d16e93
		
		Resource resource = resourceLoader.getResource(fileName);
		Preconditions.checkState(!resource.exists());
		
		resource = resourceLoader.getResource(fileProtocol + fileName);
		Preconditions.checkState(!resource.exists());
		System.out.println(resource.getFile());
		
		resource = resourceLoader.getResource(fileProtocol + path);
		Preconditions.checkState(resource.exists());
		System.out.println(resource.getFile());

	}

}
