package com.github.springMvc.test;


import java.net.MalformedURLException;

import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.github.springMvc.example.service.IsayService;

public class HessianClient {

	@Test
	public void hessianClientTest() throws MalformedURLException {
		String url = "http://localhost:8080/remote/risayService";
		HessianProxyFactory factory = new HessianProxyFactory();
		IsayService api = (IsayService) factory.create(IsayService.class, url);

		System.out.println(api.sayHello("weitang"));
		System.out.println(api.getPerson());
	}

}
