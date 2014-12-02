package spring.core.pracitce;

import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Component
public class AnnotationUtilsPracitce {

	@Test
	public void test_getAnnotation(){
		Component annotation = AnnotationUtils.getAnnotation(this.getClass().getAnnotations()[0], Component.class);
		System.out.println(annotation);
		
	}
}
