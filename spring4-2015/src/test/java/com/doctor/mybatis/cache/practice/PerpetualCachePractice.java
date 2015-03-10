package com.doctor.mybatis.cache.practice;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import org.apache.ibatis.cache.impl.PerpetualCache;
import org.junit.Test;

/**
 * mybatis3 PerpetualCache
 * ---------------------------
 * 
 * 
 * PerpetualCache 不支持多线程，因为没能实现接口的锁功能，而且内部的map不是并发的map.
 * PerpetualCache　缓存的功能是由内部的map结构来实现的．
 * 
 * @author doctor {@link https://github.com/doctorwho1986}
 *
 * @time 2015年3月10日 下午1:38:23
 */
public class PerpetualCachePractice {

	@Test
	public void test_perpetualCache() {
		String id = "test_cache";
		PerpetualCache cache = new PerpetualCache(id);// id决定缓存的唯一性，hashCode，equals方法的判断唯一性参考．和內部的map无关系
		assertThat(cache.hashCode(), equalTo(id.hashCode()));

		cache.putObject("name", "doctor who");
		assertThat(cache, equalTo(new PerpetualCache(id)));
		assertThat(cache.getSize(), equalTo(1));

		assertThat(cache.getObject("name"), equalTo("doctor who"));
		assertThat(cache.getObject("age"), equalTo(null));
	}
}
