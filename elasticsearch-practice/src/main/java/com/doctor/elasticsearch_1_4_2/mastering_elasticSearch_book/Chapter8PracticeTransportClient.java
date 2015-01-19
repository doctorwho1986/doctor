package com.doctor.elasticsearch_1_4_2.mastering_elasticSearch_book;

import java.util.List;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.search.SearchHit;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

/**
 * 
 * @author doctor
 *
 * @time   2015年1月19日 下午3:52:40
 */
public class Chapter8PracticeTransportClient {

	public static void main(String[] args) {
		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
		TransportClient client = new TransportClient(settings);
		client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));
		Person person = new Person(1L, "doctor", "man", "unknown", 2000);

		// create创建索引
		System.out.println("-------------create---------------");
		IndexResponse indexResponse = client.prepareIndex(EsField.index, EsField.type, String.valueOf(person.getId())).setSource(person.toString()).execute().actionGet();
		System.out.println(indexResponse.isCreated());
		System.out.println(indexResponse.getId());
		System.out.println(indexResponse.getIndex());
		System.out.println(indexResponse.getType());
		System.out.println(indexResponse.getVersion());
		System.out.println(indexResponse.getHeaders());
		System.out.println(indexResponse.getContext());

		// retrieve 
		System.out.println("-------------create - retrieve---------------");
		GetResponse getResponse = client.prepareGet(EsField.index, EsField.type, String.valueOf(person.getId())).execute().actionGet();
		System.out.println(getResponse.isExists());
		System.out.println(getResponse.getId());
		System.out.println(getResponse.getIndex());
		System.out.println(getResponse.getType());
		System.out.println(getResponse.getVersion());
		System.out.println(getResponse.getSourceAsString());

		// update
		// Conditional modifications using scripting
		// Sometimes it is convenient to add some additional logic when modifying a document
		// and that's why ElasticSearch allows us to use scripting along with the update API.
		// For example, we can send a request like the following one:

		System.out.println("-------------Conditional - update---------------");
		UpdateResponse updateResponse = client.prepareUpdate(EsField.index, EsField.type, String.valueOf(person.getId())).setScript("ctx._source.name = " + EsField.name, ScriptService.ScriptType.INLINE)
				.addScriptParam(EsField.name, "docto who").execute().actionGet();
		System.out.println(updateResponse.isCreated());
		System.out.println(updateResponse.getId());
		System.out.println(updateResponse.getIndex());
		System.out.println(updateResponse.getType());
		System.out.println(updateResponse.getVersion());
		System.out.println(updateResponse.getContext());

		// retrieve
		System.out.println("-------------Conditional - update - retrieve---------------");
		getResponse = client.prepareGet(EsField.index, EsField.type, String.valueOf(person.getId())).execute().actionGet();
		System.out.println(getResponse.isExists());
		System.out.println(getResponse.getId());
		System.out.println(getResponse.getIndex());
		System.out.println(getResponse.getType());
		System.out.println(getResponse.getVersion());
		System.out.println(getResponse.getSourceAsString());

		// update
		// Simple field update
		// The case is to change a single field
		System.out.println("-------------Simple field update ---------------");
		updateResponse = client.prepareUpdate(EsField.index, EsField.type, String.valueOf(person.getId())).setDoc(EsField.address, "不知道").execute().actionGet();
		System.out.println(updateResponse.isCreated());
		System.out.println(updateResponse.getId());
		System.out.println(updateResponse.getIndex());
		System.out.println(updateResponse.getType());
		System.out.println(updateResponse.getVersion());
		System.out.println(updateResponse.getContext());

		// retrieve
		System.out.println("-------------Simple field update - retrieve---------------");
		getResponse = client.prepareGet(EsField.index, EsField.type, String.valueOf(person.getId())).execute().actionGet();
		System.out.println(getResponse.isExists());
		System.out.println(getResponse.getId());
		System.out.println(getResponse.getIndex());
		System.out.println(getResponse.getType());
		System.out.println(getResponse.getVersion());
		System.out.println(getResponse.getSourceAsString());

		// // delete
		// DeleteResponse deleteResponse = client.prepareDelete(EsField.index, EsField.type, String.valueOf(person.getId())).execute().actionGet();
		// System.out.println(deleteResponse.isFound());
		//
		// // retrieve
		// System.out.println("-------------delete - retrieve---------------");
		// getResponse = client.prepareGet(EsField.index, EsField.type, String.valueOf(person.getId())).execute().actionGet();
		// System.out.println(getResponse.isExists());

		//
		for (Person p : PersonDb.list) {
			indexResponse = client.prepareIndex(EsField.index, EsField.type, String.valueOf(p.getId())).setSource(p.toString()).execute().actionGet();
			System.out.println();
			System.out.print(indexResponse.isCreated());

		}
		System.out.println();
		// query 查询数据
		QueryBuilder queryBuilder = QueryBuilders.disMaxQuery()
				.add(QueryBuilders.termQuery(EsField.age, "12"))
				.add(QueryBuilders.prefixQuery(EsField.name, "小"));
		System.out.println("queryBuilder:" + queryBuilder);
		SearchResponse searchResponse = client.prepareSearch(EsField.index).setQuery(queryBuilder).execute().actionGet();
		for (SearchHit searchHit : searchResponse.getHits().getHits()) {
			System.out.println(searchHit.getIndex());
			System.out.println(searchHit.getId());
			System.out.println(searchHit.getScore());
			System.out.println(searchHit.getType());
			System.out.println(searchHit.getVersion());
			System.out.println(searchHit.getSourceAsString());
		}

		// 查询年龄是多少的所有人
		System.out.println("---------rangeQuery Query----------------");
		queryBuilder = QueryBuilders.rangeQuery(EsField.age).gte(12L).lte(12L);
		searchResponse = client.prepareSearch(EsField.index).setQuery(queryBuilder).execute().actionGet();
		for (SearchHit searchHit : searchResponse.getHits().getHits()) {
			System.out.println(searchHit.getIndex());
			System.out.println(searchHit.getId());
			System.out.println(searchHit.getScore());
			System.out.println(searchHit.getType());
			System.out.println(searchHit.getVersion());
			System.out.println(searchHit.getSourceAsString());
		}

		client.close();
	}

	private static interface EsField {
		String index = "esdb";
		String type = "person";
		String name = "name";
		String sex = "sex";
		String address = "address";
		String age = "age";
	}

	private static class PersonDb {
		public static final List<Person> list = Lists.newArrayList(
				new Person(2L, "小1", "man", "不知道ss", 12),
				new Person(3L, "小2", "man", "不知道xx", 12),
				new Person(4L, "小3", "man", "不知道ee", 12),
				new Person(5L, "小4", "man", "不知道oo", 123),
				new Person(6L, "小5", "man", "不知道d", 132),
				new Person(7L, "小6", "man", "不知道a", 132),
				new Person(8L, "小7", "man", "不知道sa", 162),
				new Person(9L, "小8", "man", "不知道d", 12),
				new Person(10L, "小9", "man", "不知道bvb", 182),
				new Person(11L, "小10", "man", "不知道sdg", 102)
				);
	}

	private static class Person {
		private Long id;
		private String name;
		private String sex;
		private String address;
		private Integer age;

		public Person() {

		}

		public Person(Long id, String name, String sex, String address, Integer age) {
			this.id = id;
			this.name = name;
			this.sex = sex;
			this.address = address;
			this.age = age;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		@Override
		public String toString() {
			return JSON.toJSONString(this);
		}

	}
}
