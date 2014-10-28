package com.doctor.kafkajstrom;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.doctor.kafkajstrom.bolt.KafkaBolt;
import com.doctor.kafkajstrom.util.ConfigUtil;

import nl.minvenj.nfi.storm.kafka.KafkaSpout;
import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

/**
 * 提交教程https://github.com/alibaba/jstorm/wiki/%E5%BA%94%E7%94%A8%E4%BE%8B%E5%AD%90
 * @author doctor
 *
 * @time   2014年10月24日 下午4:10:01
 */

public class KafkaJstormMain {
	private static final Logger LOG = LoggerFactory.getLogger(KafkaJstormMain.class);
	
	public static void main(String[] args) {
		
		
		TopologyBuilder builder = new TopologyBuilder();

		builder.setSpout("kafka-spout", new KafkaSpout());
		builder.setBolt("kafka-bolt", new KafkaBolt("learningJstormConfig/spring-kafkabolt-context.xml")).allGrouping("kafka-spout");
		

		Properties props = new Properties();
		try {
			props.load(KafkaJstormMain.class.getResourceAsStream("/learningJstormDefaultPop/kafka-config.properties"));
		} catch (IOException e) {
			throw new IllegalArgumentException("learningJstormDefaultPop/kafka-config.properties io error");
		}
		
		Config config = ConfigUtil.of(props);
		
//		LocalCluster cluster = new LocalCluster();
//		cluster.submitTopology("my-topology", config, builder.createTopology());
		
		try {
			StormSubmitter.submitTopology("kafka-topology", config, builder.createTopology());
		} catch (AlreadyAliveException | InvalidTopologyException e) {
			LOG.error("{msg:'StormSubmitter.submitTopologyWithProgressBar error  {}'}",e);
		}
	}

	
}
