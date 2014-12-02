package spring.core.pracitce;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class ClassPathResourcePractice {
	@Test
	public void test()throws Throwable{
		ClassPathResource classPathResource = new ClassPathResource("springCorePractice/classPathResource.txt");
		System.out.println(classPathResource.contentLength());
		System.out.println(classPathResource.exists());
		System.out.println(classPathResource.getDescription());
		System.out.println(classPathResource.getFilename());
		System.out.println(classPathResource.getPath());
		System.out.println(classPathResource.getURL());
	}
}
