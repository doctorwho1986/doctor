package nl.minvenj.nfi.storm.kafka.util;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import backtype.storm.Config;

import com.doctor.kafkajstrom.KafkaJstormMain;
import com.doctor.kafkajstrom.util.ConfigUtil;

public class ConfigUtilsTest {

	@Test
	public void test_createKafkaConfig(){
		Properties props = new Properties();
		try {
			props.load(KafkaJstormMain.class.getResourceAsStream("/learningJstormDefaultPop/kafka-config.properties"));
		} catch (IOException e) {
			throw new IllegalArgumentException("learningJstormDefaultPop/kafka-config.properties io error");
		}
		
		Config config = ConfigUtil.of(props);
		final Object consumerTimeout = props.getProperty("kafka.consumer.timeout.ms");
		System.out.println(consumerTimeout);
		  if (consumerTimeout == null || Integer.parseInt(String.valueOf(consumerTimeout)) < 0) {
	            throw new IllegalArgumentException("kafka configuration value for 'consumer.timeout.ms' is not suitable for operation in storm");
	        }
	}
}
