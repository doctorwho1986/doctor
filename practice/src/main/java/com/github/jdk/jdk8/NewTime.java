package com.github.jdk.jdk8;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewTime {

	public static void main(String[] args) {
		System.out.println(Instant.now());
		System.out.println(LocalDateTime.now());
		System.out.println(LocalDateTime.parse("2013-12-01T11:00:00"));
		
		LocalDateTime dateTime = LocalDateTime.now().plusHours(12);
		System.out.println(dateTime);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		String format = dateTime.format(formatter );
		System.out.println(format);
		formatter();
	}
	
	
	public static void formatter() {
		String format = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println("formatter: " + format);
		
		//数据库LocalDateTime->Timestamp
		Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
		System.out.println(timestamp);
		
		//Timestamp -> LocalDateTime 精度多了，转换会麻烦吗
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String timestampS = timestamp.toString();
		timestampS = timestampS.substring(0, timestampS.lastIndexOf('.'));
		System.out.println(timestampS);
		String formatTimestamp = LocalDateTime.parse(timestampS,dateTimeFormatter).format(dateTimeFormatter);
		System.out.println("Timestamp formatter :" + formatTimestamp);
		
	}

}
