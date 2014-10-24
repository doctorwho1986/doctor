package com.doctor.kafkajstrom;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import kafka.serializer.Decoder;
import kafka.serializer.StringDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.doctor.kafkajstrom.util.ConfigUtil;

import nl.minvenj.nfi.storm.kafka.KafkaSpout;
import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

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
		builder.setBolt("kafka-bolt", new KafkaBolt()).allGrouping("kafka-spout");
		

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

	public static class KafkaBolt extends BaseRichBolt {
		
		private static final Logger LOG = LoggerFactory.getLogger(KafkaBolt.class);
		private static final long serialVersionUID = 1L;
		
		private StringDecoder stringDecoder = new StringDecoder(null);

		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			// TODO Auto-generated method stub

		}

		@Override
		public void execute(Tuple input) {
			byte[] message = input.getBinaryByField("bytes");
			String logMessage = stringDecoder.fromBytes(message);
			System.out.println(logMessage);
			LOG.info("{msg:'{}  获得数据  {}'}", Thread.currentThread().getName() + " -- " + KafkaBolt.class.getName(), logMessage);

		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			// TODO Auto-generated method stub

		}

	}
}
