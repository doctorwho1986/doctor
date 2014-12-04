package spring.core.pracitce;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringApplicationEventConfigure {
	
	@Bean(name="pigEventListener")
	public PigEventListener pigEventListener(){
		return new PigEventListener();
	}
	
	@Bean(name="pig")
	public Pig pig(){
		return new Pig();
	}
	
	public static class PigSpeakEvent extends ApplicationEvent{

		public PigSpeakEvent(Object source) {
			super(source);
		}
		
	}
	
	public static class PigEventListener implements ApplicationListener<PigSpeakEvent>{

		@Override
		public void onApplicationEvent(PigSpeakEvent event) {
			System.out.println(event.getClass().getName() + "is run -----------------");
			
		}
		
	}
	
	public static class Pig implements ApplicationContextAware{
		private ApplicationContext applicationContext;
		@Override
		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
			this.applicationContext = applicationContext;
		}
		
		public void speak(){
			applicationContext.publishEvent(new PigSpeakEvent(this));
		}
	}
}
