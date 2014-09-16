/*
 * Copyright (C) 2014 The doctor Authors
 * https://github.com/doctorwho1986
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jstorm.gettingstartedwithstorm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.jstorm.local.LocalCluster;

import backtype.storm.Config;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
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

/**
 * @author doctor
 *
 * @date 2014年9月16日 下午9:03:18
 */
public class TopologiesCh03 {

	/**
	 * @param args
	 * @throws InvalidTopologyException
	 * @throws AlreadyAliveException
	 */
	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException {
		// Topology definition
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader", new WordReaderSpoutCh03());
		builder.setSpout("signals-spout", new SignalsSpoutCh03());
		builder.setBolt("word-normalizer", new WordTransformBoltCh03())
				.shuffleGrouping("word-reader");

		builder.setBolt("word-counter", new WordCounterBoltCh03(), 2)
				.fieldsGrouping("word-normalizer", new Fields("word"))
				.allGrouping("signals-spout", "signals");

		// Configuration
		Config conf = new Config();
		conf.put("wordsFile", "src/main/resources/jstorm/wordsFile");
		conf.setDebug(true);
		// Topology run
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Count-Word-Toplogy-With-Refresh-Cache", conf, builder.createTopology());

		try {
			TimeUnit.MINUTES.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		cluster.shutdown();

	}

}

class WordReaderSpoutCh03 extends BaseRichSpout {
	private static final Logger log = LoggerFactory.getLogger(WordReaderSpoutCh03.class);

	private static final long serialVersionUID = 1L;

	private TopologyContext context;

	private SpoutOutputCollector collector;

	private Path path;

	private boolean isCompleted = false;

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.context = context;
		this.path = Paths.get("", (String) conf.get("wordsFile"));
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

		if (isCompleted) {
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (Throwable e) {

			}
			return;
		}

		try {
			List<String> allLines = Files.readAllLines(path);
			for (String line : allLines) {
				this.collector.emit(new Values(line));
			}
		} catch (IOException e) {
			log.error("{emit error: '{}'}", e.getMessage());
			throw new RuntimeException("emit error:" + e.getMessage());
		} finally {
			isCompleted = true;
		}

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));

	}

}

class SignalsSpoutCh03 extends BaseRichSpout {

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

class WordTransformBoltCh03 extends BaseRichBolt {

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

class WordCounterBoltCh03 extends BaseRichBolt {
	private static final Logger log = LoggerFactory.getLogger(WordCounterBoltCh03.class);

	private static final long serialVersionUID = 1L;
	private Map<String, Integer> countMap;
	private OutputCollector collector;
	private String name;
	private int id;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.countMap = new HashMap<>();
		this.collector = collector;
		this.name = context.getThisComponentId();
		this.id = context.getThisTaskId();
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
		countMap.forEach((k, v) -> {
			log.info("{clean up.................}");
			log.info("k : {} , v : {}", k, v);
		});
	}

}
