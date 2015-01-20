package com.doctor.elasticsearch_1_4_2;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.search.SearchHit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
/**
 * 官方文档练习
 * http://www.elasticsearch.org/guide/en/elasticsearch/client/java-api/current/index_.html
 * @author doctor
 *
 * @since 2015年1月20日 上午12:28:38
 */
public class ElasticsearchGuideClient {
	private TransportClient transportClient;

	@Before
	public void init() {
		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "elasticsearch").build();
		transportClient = new TransportClient(settings);
		transportClient.addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
	}

	@After
	public void destroy() {
		transportClient.close();
	}

	/**
	 * index api
	 * @see http://www.elasticsearch.org/guide/en/elasticsearch/client/java-api/current/index_.html
	 */
	@Test
	public void test_index() {
		for (Tweet tweet : TweetDb.Tweets) {
			IndexResponse actionGet = transportClient.prepareIndex(TweetField.index, TweetField.type, String.valueOf(tweet.getId()))
							.setSource(tweet.toString()).execute().actionGet();
			System.out.println(tweet+ "index :" + actionGet.isCreated());
		}
	}
	
	/**
	 * @see http://www.elasticsearch.org/guide/en/elasticsearch/client/java-api/current/get.html
	 */
	@Test
	public void test_get_api(){
		GetResponse actionGet = transportClient.prepareGet(TweetField.index, TweetField.type, String.valueOf(2)).execute().actionGet();
		System.out.println("---------------test_get_api----------------");
		System.out.println("getIndex:" + actionGet.getIndex());
		System.out.println("getType:" + actionGet.getType());
		System.out.println("getId:" + actionGet.getId());
		System.out.println("getVersion:" + actionGet.getVersion());
		System.out.println("getSourceAsString:" + actionGet.getSourceAsString());
		System.out.println("isExists:" + actionGet.isExists());
	}
	
	@Test
	public void test_delete_api(){
		DeleteResponse deleteResponse = transportClient.prepareDelete(TweetField.index, TweetField.type, String.valueOf(1)).execute().actionGet();
		System.out.println("----------------test_delete_api----------------");
		System.out.println("getIndex:" + deleteResponse.getIndex());
		System.out.println("getType:" + deleteResponse.getType());
		System.out.println("getId:" + deleteResponse.getId());
		System.out.println("isFound:" + deleteResponse.isFound());
	}

	/**
	 * @see http://www.elasticsearch.org/guide/en/elasticsearch/client/java-api/current/java-update-api.html
	 */
	@Test
	public void test_update_api_use_UpdateRequest(){
		//1.use UpdateRequest
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(TweetField.index);
		updateRequest.type(TweetField.type);
		updateRequest.id(String.valueOf(2L));
		updateRequest.doc(TweetField.message, "use UpdateRequest");
		UpdateResponse actionGet = transportClient.update(updateRequest).actionGet();
		System.out.println("-----------use UpdateRequest-----------");
		
	}
	
	@Test
	public void test_update_use_setScript(){
		UpdateResponse updateResponse = transportClient.prepareUpdate(TweetField.index, TweetField.type, String.valueOf(2L))
		                .setScript("ctx._source."+TweetField.message+"=\"update_use_setScript\"", ScriptService.ScriptType.INLINE).execute().actionGet();
		 test_get_api();
		
	}
	
	/**
	 * setScript setDoc 不能同时用，setDoc 用的时候，只能用一次，即 .setDoc 出现一次，如果两次，最后的.setDoc只生效
	 * 
	 */
	@Test
	public void test_update_use_setDoc(){
		UpdateResponse updateResponse = transportClient.prepareUpdate(TweetField.index, TweetField.type, String.valueOf(2L))
						.setDoc(TweetField.message, "update_use_doc")
						.execute().actionGet();
		test_get_api();
		
		Map<String, Object> map = new HashMap<>();
		map.put(TweetField.message, "update_use_doc 2");
		map.put(TweetField.user, "doctor 12");
		updateResponse = transportClient.prepareUpdate(TweetField.index, TweetField.type, String.valueOf(2L))
				.setDoc(map)
				.execute().actionGet();
        test_get_api();
		
	}
	
	
	
	@Test
	public void test_bulk_update(){
		BulkRequestBuilder bulkRequestBuilder = transportClient.prepareBulk();
		for (Tweet tweet : TweetDb.Tweets2) {
			IndexRequestBuilder indexRequestBuilder = transportClient.prepareIndex(TweetField.index, TweetField.type, String.valueOf(tweet.getId()))
			               .setSource(tweet.toString());
			bulkRequestBuilder.add(indexRequestBuilder);
		}
		
		 BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();
		 System.out.println("hasFailures:" + bulkResponse.hasFailures());
		 
		 System.out.println("---------bulk_update------------");
		 SearchResponse searchResponse = transportClient.prepareSearch(TweetField.index).setTypes(TweetField.type)
				 										.setQuery(QueryBuilders.rangeQuery(TweetField.id).gte(0L).lte(100L))
				 										.setSize(Integer.MAX_VALUE) //这个默认返回10个，必须设定
				 										.execute().actionGet();
		 System.out.println(searchResponse.getHits().getHits().length);
		 for (SearchHit searchHit : searchResponse.getHits().getHits()) {
			print(searchHit);
		}
	}
	
	@Test
	public void test_use_scroll_search(){
		SearchResponse searchResponse = transportClient.prepareSearch(TweetField.index)
				       .setTypes(TweetField.type)
				       .setSearchType(SearchType.SCAN)
			           .setScroll(new TimeValue(600000))
			           .setQuery(QueryBuilders.rangeQuery(TweetField.id).gte(1L).lte(100L))
			           .setSize(10).execute().actionGet();

		while (true) {
			 for (SearchHit searchHit : searchResponse.getHits().getHits()) {
					print(searchHit);
				 }
			searchResponse = transportClient
					.prepareSearchScroll(searchResponse.getScrollId())
					.setScroll(new TimeValue(600000)).execute()
					.actionGet();
			if (searchResponse.getHits().getHits().length == 0) {
				break;
			}
		}
		
	}
	
	
	private static void print(SearchHit searchHit){
		System.out.println("getIndex:" + searchHit.getIndex());
		System.out.println("getType:" + searchHit.getType());
		System.out.println("getId:" + searchHit.getId());
		System.out.println("getScore:" + searchHit.getScore());
		System.out.println("getVersion:" + searchHit.getVersion());
		System.out.println("getSourceAsString:" + searchHit.getSourceAsString());
	}
	
	
	private static interface TweetField{
		String index = "twitter";
		String type = "tweet";
		String user = "user";
		String message = "message";
		String postDate = "postDate";
		String id = "id";
	}
	private static class TweetDb {
		public static final List<Tweet> Tweets = Arrays.asList(
				new Tweet(1L, "doctor", LocalDateTime.now(), "hello es"),
				new Tweet(2L, "kimchy", LocalDateTime.now().minusDays(2L), "hello kimchy"),
				new Tweet(3L, "sim", LocalDateTime.now().minusDays(4L), "hello sim"),
				new Tweet(4L, "madi", LocalDateTime.now().minusMonths(1L), "hello madi"),
				new Tweet(5L, "ming", LocalDateTime.now().plusDays(3L), "hello min")
				);
		
		public static final List<Tweet>  Tweets2 = Arrays.asList(
				new Tweet(6L, "udoctor6", LocalDateTime.now(), "uhello es8"),
				new Tweet(7L, "ukimchy6", LocalDateTime.now().minusDays(21L), "uhello g kimchy"),
				new Tweet(8L, "usim6", LocalDateTime.now().minusDays(14L), "uhello m sim"),
				new Tweet(9L, "umadi6", LocalDateTime.now().minusMonths(11L), " uh hello madi"),
				new Tweet(10L, "uming6", LocalDateTime.now().plusDays(13L), "m hello min")
				);
	}

	private static class Tweet {
		private Long id;
		private String user;
		private LocalDateTime postDate;
		private String message;

		public Tweet() {

		}

		public Tweet(Long id, String user, LocalDateTime postDate, String message) {
			this.id = id;
			this.user = user;
			this.postDate = postDate;
			this.message = message;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public LocalDateTime getPostDate() {
			return postDate;
		}

		public void setPostDate(LocalDateTime postDate) {
			this.postDate = postDate;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return JSON.toJSONString(this);
		}

		public static void main(String[] args) {
			Tweet tweet = new Tweet(1L, "doctor", LocalDateTime.now(), "hello");
			System.out.println(tweet);
			Tweet parseObject = JSON.parseObject(tweet.toString(), Tweet.class);
			System.out.println(parseObject);
		}
	}
}
