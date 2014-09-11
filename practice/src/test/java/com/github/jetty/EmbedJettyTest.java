package com.github.jetty;

import static org.junit.Assert.fail;
import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class EmbedJettyTest {

	private static Logger logger = LoggerFactory.getLogger(EmbedJettyTest.class);
	private static String contextPath = "/jetty";
	private static String webAppRelativePath = "/src/test/resources/webapps/webapp";
	private static String contextPath2 = "/jetty2";
	private static String webAppRelativePath2 = "/src/test/resources/webapps/webapp2";

	@Before
	public void init() {
	}

	@Test
	public void testEmbedJettyStringString() {
		EmbedJetty jetty = new EmbedJetty(contextPath, webAppRelativePath);
		jetty.addServlet(contextPath, "/js", new HttpServlet() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void doGet(HttpServletRequest req,
					HttpServletResponse resp) throws ServletException,
					IOException {
				doPost(req, resp);
			}

			@Override
			protected void doPost(HttpServletRequest req,
					HttpServletResponse resp) throws ServletException,
					IOException {
				System.out.println("servlet post");
			}
		});
		jetty.start();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpUriRequest request = RequestBuilder.get().setUri("http://localhost:8080/jetty/js").build();
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(request);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(response.getStatusLine());
		Assert.assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
		jetty.stop();
	}

	@Test
	public void testEmbedJettyIntStringStringStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddServlet() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFilter() throws IOException, SAXException {
		EmbedJetty jetty = new EmbedJetty(contextPath, webAppRelativePath);
		jetty.addFilter(contextPath, "/f", new Filter() {
			public void init(FilterConfig filterConfig) throws ServletException {
				logger.info("{msg:'Filter init'}");
			}

			public void doFilter(ServletRequest request, ServletResponse response,
					FilterChain chain) throws IOException, ServletException {
				logger.info("Filter doFilter");
			}

			public void destroy() {
				logger.info("Filter destroy");
			}
		}, DispatcherType.REQUEST, DispatcherType.FORWARD);
		jetty.start();
		WebConversation webConversation = new WebConversation();
		WebResponse response = webConversation.getResponse("http://localhost:8080/jetty/f");
		Assert.assertEquals(org.eclipse.jetty.http.HttpStatus.OK_200, response.getResponseCode());
		jetty.stop();
	}

	@Test
	public void testStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testStop() {
		fail("Not yet implemented");
	}

	@Test
	public void testOneHostManyWebAapp() throws IOException, SAXException {
		EmbedJetty jetty = new EmbedJetty(contextPath, webAppRelativePath);
		jetty.addFilter(contextPath, "/f", new Filter() {
			public void init(FilterConfig filterConfig) throws ServletException {
				logger.info("{msg:'{} filter init'}", contextPath);
			}

			public void doFilter(ServletRequest request, ServletResponse response,
					FilterChain chain) throws IOException, ServletException {
				logger.info("{msg:'{} filter doFilter'}", contextPath);
			}

			public void destroy() {
				logger.info("{msg:'{} filter destroy' }", contextPath);
			}
		}, DispatcherType.REQUEST);
		jetty.addWebAppContext(contextPath2, webAppRelativePath2);
		jetty.addFilter(contextPath2, "/f2", new Filter() {
			public void init(FilterConfig filterConfig) throws ServletException {
				logger.info("{msg:'{} filter init'}", contextPath2);
			}

			public void doFilter(ServletRequest request, ServletResponse response,
					FilterChain chain) throws IOException, ServletException {
				logger.info("{msg:'{} filter doFilter'}", contextPath2);
			}

			public void destroy() {
				// TODO Auto-generated method stub
				logger.info("{msg:'{} filter destroy' }", contextPath2);
			}
		}, DispatcherType.REQUEST);
		jetty.start();
		WebConversation webConversation = new WebConversation();
		WebResponse response = webConversation.getResponse("http://localhost:8080/jetty/f");
		Assert.assertEquals(org.eclipse.jetty.http.HttpStatus.OK_200, response.getResponseCode());
		webConversation.getResponse("http://localhost:8080/jetty2/f2");
		Assert.assertEquals(org.eclipse.jetty.http.HttpStatus.OK_200, response.getResponseCode());
		jetty.stop();
	}

	@Test
	public void testOneHostOneWebAapp() throws IOException, SAXException {
		EmbedJetty jetty = new EmbedJetty(8080, "localhost", contextPath, webAppRelativePath, null);
		jetty.addFilter(contextPath, "/f", new Filter() {
			public void init(FilterConfig filterConfig) throws ServletException {
				logger.info("{msg:'{} filter init'}", contextPath);
			}

			public void doFilter(ServletRequest request, ServletResponse response,
					FilterChain chain) throws IOException, ServletException {
				logger.info("{msg:'{} filter doFilter'}", contextPath);
			}

			public void destroy() {
				logger.info("{msg:'{} filter destroy' }", contextPath);
			}
		}, DispatcherType.REQUEST);
		// TODO: ××××××××××这里需要手动增加host配置，注意
		jetty.addWebAppContext(contextPath2, webAppRelativePath2, "www.aicai.com");
		jetty.addFilter(contextPath2, "/f2", new Filter() {
			public void init(FilterConfig filterConfig) throws ServletException {
				logger.info("{msg:'{} filter init'}", contextPath2);
			}

			public void doFilter(ServletRequest request, ServletResponse response,
					FilterChain chain) throws IOException, ServletException {
				logger.info("{msg:'{} filter doFilter'}", contextPath2);
			}

			public void destroy() {
				// TODO Auto-generated method stub
				logger.info("{msg:'{} filter destroy' }", contextPath2);
			}
		}, DispatcherType.REQUEST);
		jetty.start();
		WebConversation webConversation = new WebConversation();
		WebResponse response = webConversation.getResponse("http://localhost:8080/jetty/f");
		Assert.assertEquals(org.eclipse.jetty.http.HttpStatus.OK_200, response.getResponseCode());
		webConversation.getResponse("http://www.aicai.com:8080/jetty2/f2");
		Assert.assertEquals(org.eclipse.jetty.http.HttpStatus.OK_200, response.getResponseCode());
		jetty.stop();
	}

}
