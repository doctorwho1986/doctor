package spring.core.pracitce;

import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

/**
 * spring 配置资源路径时候，classpath:/，classpath:，不带前缀的区别，
 * 其实没区别，spring 规定 "classpath:" pseudo-URL，伪url路径，在处理这种路径前缀
 * 时候，会把这个伪url去掉。
 * @author doctor
 *
 * @time   2014年12月2日 下午6:28:12
 */
public class DefaultResourceLoaderPractice {
	@Test
	public void test_defaultResourceLoader()throws Throwable{
		DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
		Resource resource = defaultResourceLoader.getResource("springCorePractice/classPathResource.txt");
		
		System.out.println(resource.getFile());
	}
	
	@Test
	public void test_defaultResourceLoader_classpath()throws Throwable{
		DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
		Resource resource = defaultResourceLoader.getResource("classpath:springCorePractice/classPathResource.txt");
		
		System.out.println(resource.getFile());
	}
	@Test
	public void test_defaultResourceLoader_classpath_1()throws Throwable{
		DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
		Resource resource = defaultResourceLoader.getResource("classpath:/springCorePractice/classPathResource.txt");
		
		System.out.println(resource.getFile());
	}
}
