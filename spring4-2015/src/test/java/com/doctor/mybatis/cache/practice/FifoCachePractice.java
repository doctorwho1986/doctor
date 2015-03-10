package com.doctor.mybatis.cache.practice;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.*;

import org.apache.ibatis.cache.decorators.FifoCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.junit.Test;

/**
 * mybatis3 FifoCache
 * ---------------------------
 * 
 * １.FifoCache采用组合模式，FifoCache内部有一个维护具有长度限制的key键值链表，和一个底层的实际实现缓存．
 * ２.key链表主要是为何key的fifo顺序，而缓存存储主要交给了底层的缓存．
 * 
 * 3.key链表用LinkedList实现，维护key的时候多线程下需要处理，那用数据结构中的数组实现环形链表如何，
 * 能避免多线程吗．
 * 
 * 3.同样，缓存不支持多线程，因为锁没具体实现．
 * 
 * @author doctor {@link https://github.com/doctorwho1986}
 *
 * @time 2015年3月10日
 */
public class FifoCachePractice {

	@Test
	public void test_FifoCache() {
		FifoCache fifoCache = new FifoCache(new PerpetualCache("test_fifo_cache"));
		fifoCache.setSize(2);
		assertThat(fifoCache.getSize(), equalTo(0));

		fifoCache.putObject("name", "doctor");
		assertThat(fifoCache.getSize(), equalTo(1));

		assertThat(fifoCache.getObject("name"), equalTo("doctor"));

		fifoCache.putObject("age", 2000);
		assertThat(fifoCache.getSize(), equalTo(2));
		fifoCache.putObject("sex", "man");
		assertThat(fifoCache.getSize(), equalTo(2));

		assertThat(fifoCache.getObject("name"), equalTo(null));// 第一个缓存对象被移除

	}
}
