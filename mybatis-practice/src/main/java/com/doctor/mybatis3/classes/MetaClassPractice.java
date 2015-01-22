package com.doctor.mybatis3.classes;

import java.util.Arrays;

import org.apache.ibatis.reflection.MetaClass;

import com.doctor.mybatis3practice.domain.Blog;
import com.google.common.base.Preconditions;
/**
 * {@code MetaClass}封装类 {@code Reflector}，代理
 * 
 * @author doctor
 *
 * @time   2015年1月22日 上午11:08:12
 */
public class MetaClassPractice {

	public static void main(String[] args) {
		MetaClass metaClass = MetaClass.forClass(Blog.class);
		metaClass.setClassCacheEnabled(false);
		Preconditions.checkState(!metaClass.isClassCacheEnabled());
		
		String findProperty = metaClass.findProperty("authorId");
		System.out.println(findProperty);
		
		Arrays.asList(metaClass.getGetterNames()).forEach(System.out::println);

	}

}
