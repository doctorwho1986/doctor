package com.doctor.ebook.introducing_spring_framework;

import java.util.Objects;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author doctor
 *
 * 2014年12月1日 下午9:20:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ChapterConfig.class)
public class Chapter1 {
	@Autowired
	@Qualifier("helloMessage")
	private IHelloMessage helloMessage;
	
	@Test
	public void test_chapter_1(){
		Objects.nonNull(helloMessage);
		assertThat(helloMessage.hello(), equalTo("hello doctor"));
		System.out.println(helloMessage.hello());
	}
}
