package com.github.curator_zookeeper;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

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

		watchNodeData(zk, path);

		WatchPathCache(zk, namespace);

		Stat forPath = zk.checkExists().forPath(path);
		if (forPath != null) {
			zk.delete().deletingChildrenIfNeeded().forPath(path);
			LOG.info("{msg:'{} is  exist and delete'}", path);

		} else {
			LOG.warn("{msg:'{} is exist'}", path);
		}

		zk.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
				.withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
				.forPath(path);

		zk.setData().forPath(path, "doctorwho".getBytes(StandardCharsets.UTF_8));

		byte[] data = zk.getData().usingWatcher(new ZkWatch(path)).forPath(path);
		data = zk.getData().forPath(path);
		System.out.println("get:" + new String(data, StandardCharsets.UTF_8));

		while (true) {
		}

	}

	private static void watchNodeData(CuratorFramework zk, String path) throws Exception {
		final NodeCache nodeCache = new NodeCache(zk, path);
		nodeCache.getListenable().addListener(new NodeCacheListener() {

			@Override
			public void nodeChanged() throws Exception {
				final ChildData currentData = nodeCache.getCurrentData();
				Map<String, String> info = new HashMap<>();
				
				if (currentData != null) {
					info.put("path", currentData.getPath().toString());
					info.put("data", new String(currentData.getData(), StandardCharsets.UTF_8));
					info.put("stat", JSON.toJSONString(currentData.getStat()));
				}

				LOG.info("{msg:'nodeChanged info : {}'}", JSON.toJSONString(info));

			}
		}, Executors.newFixedThreadPool(2));

		nodeCache.start();
	}

	private static void WatchPathCache(CuratorFramework zk, String path) throws Exception {
		PathChildrenCache pathChildrenCache = new PathChildrenCache(zk, path, true);
		pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {

			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				Map<String, String> info = new HashMap<>();
				ChildData currentData = event.getData();
				info.put("path", currentData.getPath().toString());
				info.put("data", new String(currentData.getData(), StandardCharsets.UTF_8));
				info.put("stat", JSON.toJSONString(currentData.getStat()));

				String message = "watch path child event :";

				switch (event.getType()) {
				case CHILD_ADDED:
					message = message + " child add event";
					break;

				case CHILD_REMOVED:
					message = message + " child remove event";
					break;

				case CHILD_UPDATED:
					message = message + " child update event";
					break;
				case CONNECTION_LOST:
				case CONNECTION_RECONNECTED:
				case CONNECTION_SUSPENDED:
				case INITIALIZED:

				default:
					message = message + " unknown event";
					break;
				}

				LOG.info("{msg:{} :{}}", message, JSON.toJSONString(info));
			}
		});

		pathChildrenCache.start();
	}

	static class ZkWatch implements CuratorWatcher {
		private String path;

		ZkWatch(String path) {
			this.path = path;
		}

		@Override
		public void process(WatchedEvent event) throws Exception {
			LOG.info("{msg---:{}}", event);
			if (event.getType() == EventType.NodeDataChanged) {
				byte[] data = zk.getData().forPath(this.path);
				LOG.info("{msg:'get data {}'}", new String(data, StandardCharsets.UTF_8));
			}

		}

	}
}
