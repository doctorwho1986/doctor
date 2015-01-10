package com.doctor.springframework.web.view.messageconverter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.doctor.embeddedjetty.EmbeddedJettyServer;

/**
 * @see http://www.ibm.com/developerworks/cn/web/wa-restful/index.html
 * 
 *      1. Spring，构建 Java™ 平台和 Enterprise Edition (Java EE) 应用程序的著名框架，
 *      现在在其模型-视图-控制器（Model-View-Controller ，MVC）层支持具象状态传输 (REST)。
 *      RESTful web 服务根据客户端请求生成多个具象（representations）很重要。在本篇文章中，
 *      学习使用 HttpMessageConverter 生成多个具象。
 *      代码示例展示如何使用 RestTemplate 和 HttpMessageConverter 与服务进行通信。
 *      此外，还将学习如何使用 Spring API 和注释构建 RESTful web 服务，生成常见具象，
 *      比如 ATOM Feed、XML 和 JavaScript Object Notation (JSON)。
 * 
 *      2.随附文章，“使用 Spring 3 构建 RESTful web 服务”（参见 参考资料），
 *      介绍了使用 Spring 构建 RESTful web 服务的方式。
 *      还解释了如何使用 ContentNegotiatingViewResolver 生成多个具象，
 *      这是 RESTful web 服务的一个重要功能。
 *      本文还阐述了使用 HttpMessageConverter 生成多个具象的另一种方式，
 *      并且本文中的示例展示了如何使用 RestTemplate 和 HttpMessageConverter 与服务进行通信。
 *      
 *      　
 * 
 * @author doctor
 *
 * @time 2015年1月9日 下午5:29:56
 */
public class HttpMessageConverterPractice {

	public static void main(String[] args) throws Throwable {
		EmbeddedJettyServer server = new EmbeddedJettyServer(HttpMessageConverterPracticeConfig.class);
		server.start();
		
		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setContentType(MediaType.APPLICATION_XML);
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
		RestTemplate restTemplate = new RestTemplate();
//		String forObject = restTemplate.getForObject("http://localhost:8080/test", String.class);
//		System.out.println(forObject);
		
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8080/test", HttpMethod.GET, httpEntity, String.class);
		System.out.println(responseEntity.getBody());
		
		server.stop();

	}

}
