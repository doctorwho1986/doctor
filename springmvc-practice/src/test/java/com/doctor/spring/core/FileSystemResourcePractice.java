package com.doctor.spring.core;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;

import com.google.common.base.Preconditions;

public class FileSystemResourcePractice {

	public static void main(String[] args) throws IOException {
		String fileName = "logback-test.xml";
		String path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
		System.out.println(path);
		
		FileSystemResource fileSystemResource = new FileSystemResource(path);
		Preconditions.checkState(fileSystemResource.exists());
		Preconditions.checkState(path.equals(fileSystemResource.getPath()));
		Preconditions.checkState(fileName.equals(fileSystemResource.getFilename()));
		String content = IOUtils.toString(fileSystemResource.getInputStream());
		System.out.println(content);

		System.out.println(fileSystemResource.isReadable());
		System.out.println(fileSystemResource.isWritable());
		System.out.println(fileSystemResource.getDescription());
		System.out.println(fileSystemResource.getURI());
		System.out.println(fileSystemResource.getURL());
	}

}
