package com.doctor.elasticsearch_1_4_2.mastering_elasticSearch_book;

import java.util.concurrent.TimeUnit;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chapter8PracticeNodeBuilder {
	private static final Logger log = LoggerFactory.getLogger(Chapter8PracticeNodeBuilder.class);

	public static void main(String[] args) {
		try (Node node = NodeBuilder.nodeBuilder().clusterName("elasticsearch").client(true).build();
				Client client = node.client();) {
			
			TimeUnit.SECONDS.sleep(3);
			 

		} catch (Exception e) {
			log.error("error", e);
		}

	}

}
