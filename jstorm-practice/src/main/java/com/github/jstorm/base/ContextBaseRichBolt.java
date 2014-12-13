package com.github.jstorm.base;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.base.BaseRichBolt;

/**
 * 建个基础组件，达到复用属性作用
 * @author doctor
 *
 * @since 2014年12月14日 上午12:41:44
 */
public abstract class ContextBaseRichBolt extends BaseRichBolt {

	private static final long serialVersionUID = -202695697959515787L;

	protected Logger Log = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("rawtypes")
	protected Map stormConf;
	protected TopologyContext context;
	protected OutputCollector collector;

	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.stormConf = stormConf;
		this.context = context;
		this.collector = collector;

	}

}
