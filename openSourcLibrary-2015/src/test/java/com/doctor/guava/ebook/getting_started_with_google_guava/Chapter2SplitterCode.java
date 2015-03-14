package com.doctor.guava.ebook.getting_started_with_google_guava;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * {@code Splitter}和{@code Joiner} 这两个对于map的相反的操作，在web框架例如spring mvc中用到过．
 * 
 * 对于get　url后面的参数和pos　form中前端参数的连接和后端的拆分都有用到．每个框架的处理思维是一样的．
 * 
 * @author doctor
 *
 * @since 2015年3月14日 下午9:00:17
 */
public class Chapter2SplitterCode {

	@Test
	public void test_string_split() {
		String[] split = "a,b,,".split(","); // java内置的会忽略空内容
		assertThat(split, equalTo(Lists.newArrayList("a", "b").toArray()));
	}

	@Test
	public void test_guava_Splitter() {
		List<String> splitToList = Splitter.on(",").splitToList("a,b,,");//guava不会忽略

		assertThat(splitToList, equalTo(Lists.newArrayList("a", "b", "", "")));
	}

	@Test
	public void test_guava_Splitter_omitEmptyStrings() {
		// 或者可以自由选择的忽略　，像java一样，忽略空字符串
		List<String> splitToList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList("a,b,,");

		assertThat(splitToList, equalTo(Lists.newArrayList("a", "b")));
	}

	@Test
	public void test_guava_Splitter_onPattern() {
		List<String> splitToList = Splitter.onPattern("\\d+").omitEmptyStrings().trimResults().splitToList("a231b2");

		assertThat(splitToList, equalTo(Lists.newArrayList("a", "b")));
	}

	@Test
	public void test_guava_Splitter_() {
		Map<String, String> map = Splitter.on("&").withKeyValueSeparator("=").split("name=doctor&age=666666");

		Map<String, String> linkedHashMap = Maps.newLinkedHashMap();
		linkedHashMap.put("name", "doctor");
		linkedHashMap.put("age", "666666");
		assertThat(map, equalTo(linkedHashMap));
	}

}
