package com.doctor.kafkajstrom.component;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.springframework.stereotype.Component;


@Component
public final class WordCountResult {
	
	private static ConcurrentLinkedDeque<Data> deque ;
	
	static {
		ArrayList<Data> arrayList = new ArrayList<Data>(10);
		deque = new ConcurrentLinkedDeque<Data>(arrayList);
	}
	private WordCountResult(){
		throw new IllegalAccessError("非法操作");
	}
	
	public static synchronized void put(Data data) {
		deque.poll();
		deque.offer(data);
	}
	
	public static class Data{
		private String word;
		private int count;
		
		public Data(){
			this.word = "";
			this.count = 0;
		}
		public Data(String word,int count){
			this.word = word;
			this.count = count;
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
		
		
	}
}
