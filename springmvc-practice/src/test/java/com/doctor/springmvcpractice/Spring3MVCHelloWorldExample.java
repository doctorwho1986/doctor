package com.doctor.springmvcpractice;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.doctor.embeddedjetty.EmbeddedJettyServer4ForWebapp;

/**
 * @author doctor
 *
 * @since 2015年1月7日 下午10:57:01
 */
public class Spring3MVCHelloWorldExample {

	private EmbeddedJettyServer4ForWebapp server;
	private int port = 8080;
	private String resourceBase= "/springmvc-practice/spring3MVCHelloWorldExample";
	
	@Before
	public void init()throws Throwable{
		server = new EmbeddedJettyServer4ForWebapp(port, resourceBase);
		server.start();
	}
	
	
	@After
	public void destroy()throws Throwable{
		server.stop();
	}
	
	@Test
	public void test()throws Throwable{
		Response response = Request.Get("http://localhost:8080/welcome.do").execute();
		System.out.println(response.returnContent().asString());
		
	}

}
