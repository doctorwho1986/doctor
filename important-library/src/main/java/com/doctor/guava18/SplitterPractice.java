package com.doctor.guava18;

import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;

public class SplitterPractice {

	public static void main(String[] args) {
		String input = ",,name,sex,,,who,,";
		String[] split = input.split(",");
		for (int i = 0; i < split.length; i++) {
			System.out.println( i + " : " + split[i]);
		}

		System.out.println();
		List<String> splitToList = Splitter.on(",").splitToList(input);
		for (String string : splitToList) {
			System.out.print(string + "|");
		}
		
		System.out.println();
		List<String> splitToList2 = Splitter.on(",").trimResults().splitToList(input);
		for (String string : splitToList2) {
			System.out.print(string+"|");
		}
		
		System.out.println();
		input = "name=doctor&sex=unknown&address=unknown";
		Map<String, String> map = Splitter.on("&").withKeyValueSeparator("=").split(input);
		map.forEach((k,v) -> System.out.print(k +":" +v + "     "));
	}

}
