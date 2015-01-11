package com.doctor.spring.context.inject;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("resourceBean1")
public class ResourceBean1 {

	@Resource
	private ResourceBean2 resourceBean2;
	
	@Resource(name="resourceBean3")
	private ResourceBean3 resourceBean3;
	
	//@Resource也支持集合按Type注入
	@Resource
	public List<ResourceInterface> resourceInterfaces;

	public ResourceBean2 getResourceBean2() {
		return resourceBean2;
	}

	public ResourceBean3 getResourceBean3() {
		return resourceBean3;
	}
	
	@PostConstruct
	public void init(){
		System.out.println("@PostConstruct");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("@PreDestroy");
	}
}
