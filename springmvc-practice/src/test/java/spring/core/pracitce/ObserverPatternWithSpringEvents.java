package spring.core.pracitce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;

/**
 * @link http://www.javacodegeeks.com/2012/08/observer-pattern-with-spring-events.html
 * @author doctor
 *
 * @time   2014年12月3日 下午4:04:44
 */
public class ObserverPatternWithSpringEvents {
	@Autowired
	@Qualifier("messageEventSource")
	private MessageEventSource messageEventSource;
	private AnnotationConfigApplicationContext context;
	
	@Test 
	public void test()throws Throwable{
		context = new AnnotationConfigApplicationContext(ObserverPatternWithSpringEventsConfigure.class);
		context.getAutowireCapableBeanFactory().autowireBeanProperties(this, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, true);
		
		Thread thread = new Thread(messageEventSource);
		thread.start();
		thread.join();
	}
	
	@ContextConfiguration
	public static class ObserverPatternWithSpringEventsConfigure{
		
		@Bean(name="messageEventSource")
		public MessageEventSource messageEventSource(){
			return new MessageEventSource();
		}
		
		@Bean(name="messageListener")
		public MessageListener messageListener(){
			return new MessageListener();
		}
		
	}

	public static class MessageEvent extends ApplicationEvent{

		private static final long serialVersionUID = 4927935382440103532L;
		
		private String message = "";
		public MessageEvent(Object source,String message) {
			super(source);
			if (!StringUtils.isBlank(message)) {
				this.message = message;
			}
		}
		
		@Override
		public String toString(){
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("MessageEvent [message=").append(message).append("]");
			return stringBuilder.toString();
		}
	}
	
	public static class MessageEventSource implements Runnable,ApplicationEventPublisherAware{
		private ApplicationEventPublisher applicationEventPublisher;
		
		@Override
		public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
			this.applicationEventPublisher = applicationEventPublisher;
		}

		@Override
		public void run() {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				while (true) {
					String readLine = reader.readLine();
					applicationEventPublisher.publishEvent(new MessageEvent(this, Thread.currentThread().getName() + "---" + readLine));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static class MessageListener implements ApplicationListener<MessageEvent>{

		@Override
		public void onApplicationEvent(MessageEvent event) {
			System.out.println(Thread.currentThread().getName() + event);
		}
		
	}
}
