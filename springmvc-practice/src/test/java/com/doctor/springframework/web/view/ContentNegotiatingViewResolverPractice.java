package com.doctor.springframework.web.view;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.doctor.embeddedjetty.EmbeddedJettyServer3;


public class ContentNegotiatingViewResolverPractice {
	private EmbeddedJettyServer3 embeddedJettyServer;
	
	@Before
	public void init() throws Throwable{
		embeddedJettyServer = new EmbeddedJettyServer3("/contentNegotiatingViewResolverPractice",SpringContextConfig.class, SpringMvcConfig.class);
		embeddedJettyServer.start();
	}
	
	@Test
	public void test()throws Throwable{

		Response response = Request.Get("http://localhost:8080/test.json").execute();
		System.out.println(response.returnContent().asString());
		
		response = Request.Get("http://localhost:8080/test.htm").execute();
		System.out.println(response.returnContent().asString());
		
	}
	
	@After
	public void destroy() throws Throwable{
		embeddedJettyServer.stop();
	}
}
