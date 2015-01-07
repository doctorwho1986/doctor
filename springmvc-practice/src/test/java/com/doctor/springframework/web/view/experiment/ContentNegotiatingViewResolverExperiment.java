package com.doctor.springframework.web.view.experiment;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.junit.Test;

import com.doctor.embeddedjetty.EmbeddedJettyServer4ForWebapp;

/**
 * 实验失败，默认不支持根据路径后缀返回不同视图，需配置，参看：contentNegotiatingViewResolverPractice 资源配置
 * @author doctor
 *
 * @time   2015年1月7日 下午4:39:19
 */
public class ContentNegotiatingViewResolverExperiment {

	@Test
	public void test() throws Throwable {
		EmbeddedJettyServer4ForWebapp server = new EmbeddedJettyServer4ForWebapp(8789, "/contentNegotiatingViewResolverExperiment");
		server.start();
		Response response = Request.Get("http://localhost:8789/jetty/test.html").execute();
		System.out.println(response.returnContent().asString());

		response = Request.Get("http://localhost:8789/jetty/test.json").execute();
		System.out.println(response.returnContent().asString());
		server.stop();
	}
}
