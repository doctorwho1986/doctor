package com.doctor.springframework.web.view;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.doctor.embeddedjetty.EmbeddedJettyServer3;
/**
 * ContentNegotiatingViewResolverPractice 根据路径后缀，选择不同视图
 * @author doctor
 *
 * @time   2015年1月7日 上午10:08:24
 */
public class ContentNegotiatingViewResolverPractice2 {
	private EmbeddedJettyServer3 embeddedJettyServer;

	@Before
	public void init() throws Throwable {
		embeddedJettyServer = new EmbeddedJettyServer3("/contentNegotiatingViewResolverPractice/webapp", SpringContextConfig.class, SpringMvcConfig2.class);
		embeddedJettyServer.start();
	}

	@Test
	public void test() throws Throwable {

		Response response = Request.Get("http://localhost:8080/test.json").execute();
		System.out.println(response.returnContent().asString());

		response = Request.Get("http://localhost:8080/test.html").execute();
		System.out.println(response.returnContent().asString());

	}

	@After
	public void destroy() throws Throwable {
		embeddedJettyServer.stop();
	}
}
