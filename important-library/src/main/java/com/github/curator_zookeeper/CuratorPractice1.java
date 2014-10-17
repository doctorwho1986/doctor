package com.github.curator_zookeeper;

import java.nio.charset.StandardCharsets;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CuratorPractice1 {

	private static final Logger LOG = LoggerFactory.getLogger(CuratorPractice1.class);
	
	private static CuratorFramework zk;
	private final static String namespace = "zk";
	private final static String path = "zk/curator";

	public static void main(String[] args) throws Exception {
		zk = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
								.namespace(namespace)
								.retryPolicy(new RetryNTimes(5, 1000))
								.connectionTimeoutMs(30000)
								.build();
		zk.start();
		
		Stat forPath = zk.checkExists().forPath(path);
		if (forPath != null) {
			zk.delete().deletingChildrenIfNeeded().forPath(path);
			LOG.info("{msg:'{} is  exist and delete'}",path);
			
		}else {
			LOG.warn("{msg:'{} is exist'}",path);
		}
		
		zk.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
		.withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
		.forPath(path);
		
		
		zk.setData().forPath(path, "doctorwho".getBytes(StandardCharsets.UTF_8));
		
		byte[] data = zk.getData().usingWatcher(new ZkWatch(path)).forPath(path);
		 data = zk.getData().forPath(path);
		System.out.println("get:" + new String(data, StandardCharsets.UTF_8));
		
		while(true){}
	
	}

	static class ZkWatch implements CuratorWatcher{
		private String path;
		
		ZkWatch(String path){
			this.path = path;
		}
		
		@Override
		public void process(WatchedEvent event) throws Exception {
			LOG.info("{msg---:{}}",event);
			if (event.getType() == EventType.NodeDataChanged) {
				byte[] data = zk.getData().forPath(this.path);
				LOG.info("{msg:'get data {}'}",new String(data, StandardCharsets.UTF_8));
			}
			
		}
		
	}
}
