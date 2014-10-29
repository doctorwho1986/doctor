package com.doctor.kafkajstrom;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.doctor.kafkajstrom.util.SpringUtil;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class LocalJstormMain {

	public static void main(String[] args) {
		// Topology definition
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader", new WordReaderSpoutCh03());
		builder.setSpout("signals-spout", new SignalsSpoutCh03(),6);
		builder.setBolt("word-normalizer", new WordTransformBoltCh03(),6)
				.shuffleGrouping("word-reader");

		builder.setBolt("word-counter", new WordCounterBoltCh03(), 2)
				.fieldsGrouping("word-normalizer", new Fields("word"))
				.allGrouping("signals-spout", "signals");

		// Configuration
		Config conf = new Config();
		conf.setNumWorkers(3);
		conf.setDebug(true);
		// Topology run
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Count-Word-Toplogy-With-Refresh-Cache", conf, builder.createTopology());

//		try {
//			TimeUnit.MINUTES.sleep(2);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		cluster.killTopology("Count-Word-Toplogy-With-Refresh-Cache");
//		cluster.shutdown();

	}

	public static class WordReaderSpoutCh03 extends BaseRichSpout {
		private static final Logger log = LoggerFactory.getLogger(WordReaderSpoutCh03.class);

		private static final long serialVersionUID = 1L;

		private TopologyContext context;

		private SpoutOutputCollector collector;

		private static final String WORDS = "sjdkfjksdjfdkjaaa中涉及看对方的罚款多级";

		@Override
		public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
			this.context = context;
			this.collector = collector;

		}

		@Override
		public void ack(Object msgId) {
			log.info("{Ok:{}}", msgId);
		}

		@Override
		public void fail(Object msgId) {
			log.info("{Ok:{}}", msgId);
		}

		@Override
		public void nextTuple() {
			
			this.collector.emit(new Values(RandomStringUtils.random(6, WORDS)));
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			declarer.declare(new Fields("line"));

		}

	}

	public static class SignalsSpoutCh03 extends BaseRichSpout {

		private static final long serialVersionUID = 1L;
		private SpoutOutputCollector spoutOutputCollector;

		@Override
		public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
			this.spoutOutputCollector = collector;
		}

		@Override
		public void nextTuple() {
			this.spoutOutputCollector.emit("signals", new Values("refreshCache"));
			try {
				TimeUnit.MILLISECONDS.sleep(2);
			} catch (Throwable e) {
			}

		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			declarer.declareStream("signals", new Fields("action"));

		}

	}

	public static class WordTransformBoltCh03 extends BaseRichBolt {

		private static final long serialVersionUID = 1L;
		private OutputCollector collector;
		private int numCounterTasks = 0;

		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			this.collector = collector;
			this.numCounterTasks = context.getComponentTasks("word-counter").size();
		}

		@Override
		public void execute(Tuple input) {
			String[] lines = input.getString(0).split(" ");
			for (String line : lines) {
				String lowerCase = line.trim().toLowerCase();
				if (!lowerCase.isEmpty()) {
					this.collector.emit(new Values(line));
				}
			}

			this.collector.ack(input);
		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {
			declarer.declare(new Fields("word"));

		}
	}

	public static class WordCounterBoltCh03 extends BaseRichBolt {
		private static final Logger log = LoggerFactory.getLogger(WordCounterBoltCh03.class);

		private static final long serialVersionUID = 1L;
		private Map<String, Integer> countMap;
		private OutputCollector collector;
		private String name;
		private int id;
		
		private static final ApplicationContext applicationContext;
		static{
			applicationContext = SpringUtil.of("learningJstormConfig/spring-kafkabolt-context.xml");
			log.info("--------------ApplicationContext initialized from learningJstormConfig/spring-kafkabolt-context.xml ");
		}

		@Override
		public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
			this.countMap = new HashMap<>();
			this.collector = collector;
			this.name = context.getThisComponentId();
			this.id = context.getThisTaskId();
			log.info("-----------------WordCounterBoltCh03 prepare");
		}

		@Override
		public void execute(Tuple input) {
			String word = null;
			try {
				word = input.getStringByField("word");
			} catch (Throwable e) {
			}
			if (null != word) {
				if (!countMap.containsKey(word)) {
					countMap.put(word, 1);
				} else {
					Integer count = countMap.get(word);
					count++;
					countMap.put(word, count);
				}
			} else {
				if ("signals.".equals(input.getSourceStreamId()) && "refreshCache".equals(input.getStringByField("action"))) {
					cleanup();
					countMap.clear();
				}
			}

			this.collector.ack(input);

		}

		@Override
		public void declareOutputFields(OutputFieldsDeclarer declarer) {

		}

		@Override
		public void cleanup() {
			log.info("{cleanup................}");
			countMap.forEach((k, v) -> {
				log.info("{clean up.................}");
				log.info("k : {} , v : {}", k, v);
			});
		}

	}
}
