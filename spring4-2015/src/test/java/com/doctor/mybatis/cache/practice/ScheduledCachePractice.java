package com.doctor.mybatis.cache.practice;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import java.util.concurrent.TimeUnit;

import org.apache.ibatis.cache.decorators.ScheduledCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.junit.Test;

/**
 * mybatis3 ScheduledCache
 * ---------------------------
 * 
 * {@code ScheduledCache} 缓存的清理策略是定时处理，（定时清理全部缓存）.感觉这个没
 * 大作用，Guava 基于定时清理部分缓存的实现比较实用.
 * 
 * @author doctor {@link https://github.com/doctorwho1986}
 *
 * @time 2015年3月10日
 */
public class ScheduledCachePractice {

	@Test
	public void test_ScheduledCache() throws Throwable {
		ScheduledCache scheduledCache = new ScheduledCache(new PerpetualCache("test_ScheduledCache"));
		scheduledCache.setClearInterval(3000);
		assertThat(scheduledCache.getSize(), equalTo(0));
		scheduledCache.putObject("name", "doctor");
		scheduledCache.putObject("age", 6000);

		assertThat(scheduledCache.getSize(), equalTo(2));

		TimeUnit.SECONDS.sleep(4);

		assertThat(scheduledCache.getSize(), equalTo(0));

	}
}
