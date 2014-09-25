package com.github.zookeeper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author see Hadoop实战第2版 P365
 *
 */
public class Practice1 {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZookeeperInstance1 zInstance = new ZookeeperInstance1();
		String connectString = "localhost:2181";
		zInstance.createZKInsatnce(connectString );
		String data = "group";
		String groupPath = "/ZKgroup";
		zInstance.createPNode(groupPath , data);
		
		List<String> listMembers = zInstance.listMembers("/");
		if (null == listMembers) {
			System.err.println("无子节点");
		}else {
			System.out.println("字节点有：");
			listMembers.parallelStream().forEach(System.out::println);
		}
		zInstance.closeZK();
	}

}

class ZookeeperInstance1{
	public static final Logger log = LoggerFactory.getLogger(ZookeeperInstance1.class);
	
	protected static final int SESSION_TIMEOUT = 30000;
	protected ZooKeeper zooKeeper;
	
	Watcher watcher = new Watcher() {
		
		@Override
		public void process(WatchedEvent event) {
			log.info("{zookeeper event: ' {}'}",event.toString());
		}
	};

	
	
	public void createZKInsatnce(String connectString) throws IOException {
		zooKeeper = new ZooKeeper(connectString, ZookeeperInstance1.SESSION_TIMEOUT, watcher);
	}
	
	
	public void createPNode(String groupPath,String data) throws KeeperException, InterruptedException {
		if(null != zooKeeper.exists(groupPath, watcher)){
			log.info("node existsed : {}",groupPath );
			return;
		}
		
		String cGroupPath = zooKeeper.create(groupPath, data.getBytes(StandardCharsets.UTF_8), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		log.info("createPNode : {} {} {}",groupPath,data,cGroupPath);
	}
	
	public List<String> listMembers(String groupPath) {
		
		try {
			return zooKeeper.getChildren(groupPath, watcher);
		} catch (KeeperException | InterruptedException e) {
			return null;
		}
	}
	
	public void closeZK() throws InterruptedException {
		zooKeeper.close();
	}
}