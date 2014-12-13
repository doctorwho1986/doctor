package com.github.jstorm.base;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.base.BaseRichSpout;

/**
 * 建个基础组件，达到复用属性作用
 * @author doctor
 *
 * @since 2014年12月14日 上午12:41:59
 */
public abstract class ContextBaseRichSpout extends BaseRichSpout {

	private static final long serialVersionUID = -5841115674375850162L;

	protected Logger Log = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("rawtypes")
	protected Map conf;
	protected TopologyContext context;
	protected SpoutOutputCollector collector;

	@SuppressWarnings("rawtypes")
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.conf = conf;
		this.context = context;
		this.collector = collector;
	}


}
