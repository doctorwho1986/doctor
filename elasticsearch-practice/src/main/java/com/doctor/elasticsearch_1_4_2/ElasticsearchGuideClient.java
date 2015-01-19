package com.doctor.elasticsearch_1_4_2;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
/**
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

	private static interface TweetField{
		String index = "twitter";
		String type = "tweet";
	}
	private static class TweetDb {
		public static final List<Tweet> Tweets = Arrays.asList(
				new Tweet(1L, "doctor", LocalDateTime.now(), "hello es"),
				new Tweet(2L, "kimchy", LocalDateTime.now().minusDays(2L), "hello kimchy"),
				new Tweet(3L, "sim", LocalDateTime.now().minusDays(4L), "hello sim"),
				new Tweet(4L, "madi", LocalDateTime.now().minusMonths(1L), "hello madi"),
				new Tweet(5L, "ming", LocalDateTime.now().plusDays(3L), "hello min")
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
