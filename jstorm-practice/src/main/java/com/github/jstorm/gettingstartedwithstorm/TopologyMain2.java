package com.github.jstorm.gettingstartedwithstorm;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class TopologyMain2 {
	private static final Logger log = LoggerFactory.getLogger(TopologyMain2.class);
	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		
		//Topology definition
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader", new WordReaderSpout());
		builder.setBolt("word-normal", new WordNormalizerBolt()).shuffleGrouping("word-reader");
		builder.setBolt("word-count", new WordCounterBolt()).fieldsGrouping("word-normal", new Fields("word"));

		
		//Configuration
		Config conf = new Config();
		conf.put("wordFile", "src/main/resources/jstorm/wordsFile");
		conf.setDebug(true);
		
		//Topology run
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Getting-Started-Toplogie", conf, builder.createTopology());
		
		log.info("{cluster.submitTopology..........}");
		try {
			TimeUnit.MINUTES.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cluster.shutdown();
		
	}

}


class WordReaderSpout extends BaseRichSpout{
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(WordReaderSpout.class);
	
	private TopologyContext context;
	private SpoutOutputCollector collector;
	private Path path;
	private boolean isCompleted = false;

	@Override
	public void ack(Object msgId) {
		System.out.println("Ok msgId :" + msgId);
		log.info("{msgId {} :Ok}",msgId);
	}

	@Override
	public void fail(Object msgId) {
		System.err.println("Fail msgId :" + msgId);
		log.error("{msgId {}: Fail}", msgId);
	}

	

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		try {
			this.context = context;
			String pth = (String) conf.get("wordFile");
			log.info("{FilePath:'{}'}",pth);
			this.path = Paths.get("", pth) ;
		} catch (Exception e) {
			log.error("{open error: {}}",e.getMessage());
			throw new RuntimeException("Error reading file ["+conf.get("wordFile")+"]");
		}
		
		this.collector = collector;
		
	}

	@Override
	public void nextTuple() {
		if (isCompleted) {
			try {
				TimeUnit.MICROSECONDS.sleep(1);
			} catch (Throwable e) {
				
			}
			
			return;
		}
		
		try {
			List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
			for (String line : allLines) {
				this.collector.emit(new Values(line), line);
			}
			
		} catch (Exception e) {
			log.error("{nextTuple error: {}}",e.getMessage());
		}finally{
			isCompleted = true;
		}
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}
}

class WordNormalizerBolt extends BaseBasicBolt{
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String[] strings = input.getString(0).split(" ");
		System.out.println("///////////" + strings);
		for (String world : strings) {
			world = world.trim();
			if (!world.isEmpty()) {
				world = world.toLowerCase();
				System.out.println("///////////////////" + world);
				collector.emit(new Values(world));
			}
		}
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
		
	}
	
	
	
}

class WordCounterBolt extends BaseBasicBolt{
	private static final Logger log = LoggerFactory.getLogger(WordCounterBolt.class);
	
	private Integer id;
	private Map<String, Integer> countMap ;
	private String name;

	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		countMap = new HashMap<>();
		id = context.getThisTaskId();
		name = context.getThisComponentId();
	}

	@Override
	public void cleanup() {
		String join = String.join("-----","ThisComponentId", " : ",name," . ","ThisTaskId"," : ", id.toString());
		System.out.println(join);
		countMap.forEach((k,v) ->{
			System.out.println(k + " : " + v);
		});
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		String word = input.getString(0);
		log.info("{--execute :{}}",word);
		if (!countMap.containsKey(word)) {
			countMap.put(word, 1);
		}else {
			Integer count = countMap.get(word);
			count++;
			log.info("{{}:{}}",word,count);
			countMap.put(word, count);
		}
		
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}
	
}
