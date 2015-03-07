package com.doctor.ebook.apress_pro_spring_4th_edition.chapter3;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author doctor
 *
 * @since 2015年3月7日 上午10:47:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("spring-chapater3-config.xml")
public class Chapter3Code {

	@Resource(name="birthDate")
	private LocalDateTime birth;
	
	@Resource(name="map")
	private Map<String, Object> map;
	
	@Resource(name="props")
	private Properties props;
	
	@Resource(name="set")
	private Set<Integer> set;
	
	@Resource(name="list")
	private List<Integer> list;
	
	@Test
	public void test_util(){
		assertNotNull(birth);
		System.out.println(birth);
		
		assertNotNull(map);
		System.out.println(map);
		
		assertNotNull(props);
		System.out.println(props);
		
		assertNotNull(set);
		System.out.println(set);
		
		assertNotNull(list);
		System.out.println(list);
		
		
	}
}
