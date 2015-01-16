package com.doctor.elasticsearch_1_4_2;

import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;

public class TransportClientPractice {

	public static void main(String[] args) throws InterruptedException {
		Client client = new TransportClient();
		IndexResponse actionGet = client.prepareIndex("doctor", "name").execute().actionGet();
		System.out.println(actionGet);
		
		
		TimeUnit.SECONDS.sleep(5);
		client.close();

	}

}
