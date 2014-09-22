package com.github.jstorm.ebook.stormblueprints;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.alibaba.jstorm.local.LocalCluster;

import backtype.storm.Config;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.NotAliveException;
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
import backtype.storm.utils.Utils;

public class Chapter1DistributedWordCount {
	private static final String sentence_spout_id = "sentence-spout";
	private static final String split_sentence_bolt_id = "split_sentence_bolt";
	private static final String word_count_bolt_id = "word_count_bolt";
	private static final String report_bolt_id = "report_bolt";

	public static void main(String[] args) throws AlreadyAliveException, InvalidTopologyException, NotAliveException {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout(sentence_spout_id, new SentenceSpout1());
		builder.setBolt(split_sentence_bolt_id, new SplitSentenceBolt1()).shuffleGrouping(sentence_spout_id);
		builder.setBolt(word_count_bolt_id, new WordCountBolt1()).fieldsGrouping(split_sentence_bolt_id, new Fields(ConstrantChapter1.split_sentence_bolt_fieds));
		builder.setBolt(report_bolt_id, new ReportBolt1()).globalGrouping(word_count_bolt_id);
		
		LocalCluster localCluster = new LocalCluster();
		localCluster.submitTopology("word-count-topoogy", new Config(), builder.createTopology());
		
		try {
			TimeUnit.MINUTES.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		localCluster.killTopology("word-count-topoogy");
		localCluster.shutdown();
	}

}

class ConstrantChapter1{
	public static final String sentence_spout_fieds = "sentence";
	public static final String split_sentence_bolt_fieds = "word";
	public static final String[] wordcount_bolt_fieds = {"word","count"};
}


class SentenceSpout1 extends BaseRichSpout{
	
	private static final long serialVersionUID = 1L;
	private SpoutOutputCollector collector;
	private String[] sentences = {"who are you",
					"I you who",
					"do you know me",
					"me me who I know"};

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		
	}

	@Override
	public void nextTuple() {
		int index = (int)(Math.random() * sentences.length);
		this.collector.emit(new Values(sentences[index]));
		Utils.sleep(2);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(ConstrantChapter1.sentence_spout_fieds));
		
	}
	
}

class SplitSentenceBolt1 extends BaseRichBolt{

	private static final long serialVersionUID = 1L;
	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		
	}

	@Override
	public void execute(Tuple input) {
		String stringByField = input.getStringByField(ConstrantChapter1.sentence_spout_fieds);
		String[] split = stringByField.split(" ");
		for (String word : split) {
			this.collector.emit(new Values(word));
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(ConstrantChapter1.split_sentence_bolt_fieds));
		
	}
	
}

class WordCountBolt1 extends BaseRichBolt{
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	private Map<String, Integer> map;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		map = new HashMap<>();
	}

	@Override
	public void execute(Tuple input) {
		String word = input.getStringByField(ConstrantChapter1.split_sentence_bolt_fieds);
		Integer count = map.get(word);
		if (null == count) {
			count = 0;
		}
		count ++;
		map.put(word, count);
		this.collector.emit(new Values(word,count));
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields(ConstrantChapter1.wordcount_bolt_fieds[0],ConstrantChapter1.wordcount_bolt_fieds[1]));
		
	}
	
}

class ReportBolt1 extends BaseRichBolt{

	private static final long serialVersionUID = 1L;
	private Map<String, Integer> map;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		map = new HashMap<>();
	}

	@Override
	public void execute(Tuple input) {
		String word = input.getStringByField(ConstrantChapter1.wordcount_bolt_fieds[0]);
		Integer count = input.getIntegerByField(ConstrantChapter1.wordcount_bolt_fieds[1]);
		
		map.put(word, count);
		System.out.println(word + "------------" + count);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}
	
	@Override
	public void cleanup() {
		for (Entry<String, Integer> en : map.entrySet()) {
			System.out.println(en.getKey() + " //: // " + en.getValue());
		}
	}
	
}