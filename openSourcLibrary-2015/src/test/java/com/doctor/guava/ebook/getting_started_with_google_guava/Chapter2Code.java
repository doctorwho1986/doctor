package com.doctor.guava.ebook.getting_started_with_google_guava;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * {@code Joiner} 和 {@code MapJoiner}　的实例总是 immutable（详见java并发编程实战相关部分）
 * 
 * [ 1.状态创建后不能被修改． ２．所有的域都是final类型，并且它被正确创建（创建期间没有发生this引用的逸出） ]
 * 
 * 创建通过静态函数on，内部调用构造函数，对于设置null类型的时候，函数内部会生成一个匿名类，从而重载设置null类型的相关函数．
 * 
 * 
 * 所以他们都是线程安全的． 他们的构造函数都是私有的，必须调用他们的静态方法生成他们的对象（不用反射构造对象情况下），
 * 
 * @author doctor
 *
 * @since 2015年3月14日 上午11:45:32
 */
public class Chapter2Code {

	private final List<String> list = Lists.newArrayList("name", "doctor", "sex", "man", null);

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void test_Joiner() {

		Joiner joiner = Joiner.on(",").skipNulls();

		assertThat(joiner.join(list), equalTo("name,doctor,sex,man"));

		joiner = Joiner.on(",").useForNull("null");
		assertThat(joiner.join(list), equalTo("name,doctor,sex,man,null"));

		joiner = Joiner.on("|");
		expectedException.expect(NullPointerException.class);// 如果不处理空指针，会抛出异常．
		joiner.join(list);

	}

	@Test
	public void test_Joiner_wrong_use() {

		Joiner joiner = Joiner.on(",");
		joiner.skipNulls();// 这个方法会生成一个新的对象，对原对象的设置无影响
		expectedException.expect(NullPointerException.class);
		joiner.join(list);
	}

	@Test
	public void test_Joiner_appendTo_StringBuilder() {
		Joiner joiner = Joiner.on("|").skipNulls();
		StringBuilder builder = new StringBuilder(256);

		joiner.appendTo(builder, list);

		assertThat(builder.toString(), equalTo("name|doctor|sex|man"));
		System.out.println(builder.toString());
	}

	@Test
	public void test_Joiner_appendTo_File() throws IOException {
		File file = new File("a.txt");

		if (!file.exists()) {
			file.createNewFile();
		}

		try (FileWriter fileWriter = new FileWriter(file);) {
			Joiner.on("#").skipNulls().appendTo(fileWriter, list);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		List<String> lines = Files.readAllLines(Paths.get("a.txt"));

		System.out.println(lines);
		assertThat(Joiner.on("").join(lines), equalTo(Joiner.on("#").skipNulls().join(list)));

	}

	@Test
	public void test_MapJoiner() {
		Map<String, String> map = Maps.newLinkedHashMap();
		map.put("name", "doctor");
		map.put("sex", "man");

		String join = Joiner.on(",").withKeyValueSeparator("=").join(map);
		assertThat(join, equalTo("name=doctor,sex=man"));
	}
}
