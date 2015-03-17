package com.doctor.java;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * java8 Streams的使用，并行使用，异步使用
 * 
 * @author doctor
 *
 * @time 2015年3月17日
 */
public class ImprovingPerformanceStreams {
	private List<Integer> list = Arrays.asList(1, 2, 3, 5);

	@Test
	public void test_sequential_stream() {
		Instant start = Instant.now();
		Integer collect = list.stream().map(i -> i * 10).collect(Collectors.summingInt((t) -> t));
		Instant end = Instant.now();
		System.out.println(collect);
		System.out.println("用时:" + ChronoUnit.MILLIS.between(start, end) + " millisecond");
		System.out.println("--------------");
	}

	@Test
	public void test_parallel_stream() {
		Instant start = Instant.now();
		Integer collect = list.parallelStream().map(i -> i * 10).collect(Collectors.summingInt(i -> i));
		Instant end = Instant.now();
		System.out.println(collect);
		System.out.println("用时:" + ChronoUnit.MILLIS.between(start, end) + " millisecond");
		System.out.println("--------------");
	}

	@Test
	public void test_CompletableFuture_parallel_stream() {
		ExecutorService service = Executors.newFixedThreadPool(20);
		Instant start = Instant.now();
		List<CompletableFuture<Integer>> collect = list.stream().map(i -> CompletableFuture.supplyAsync(() -> i * 10, service)).collect(Collectors.toList());
		Integer integer = collect.stream().map(CompletableFuture::join).collect(Collectors.summingInt(i -> i));
		Instant end = Instant.now();
		System.out.println(integer);
		System.out.println("用时:" + ChronoUnit.MILLIS.between(start, end) + " millisecond");
		System.out.println("--------------");
	}

	/*
	 * 
	 * 看下输出结果：
	 * 110
	 * 用时:78 millisecond
	 * --------------
	 * 110
	 * 用时:7 millisecond
	 * --------------
	 * 110
	 * 用时:3 millisecond
	 * --------------
	 * 请调整线程池大小，看其结果．
	 * ＩＯ计算型，看看结果如何．
	 */
}
