package com.doctor.lucene.analysis;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class CJKAnalyzerPractice {

	public static void main(String[] args) throws Throwable {
		String content = "I can't beleive that the Carolina Hurricanes won the 2005-2006 Stanley Cup.";
		Reader stringReader = new StringReader(content);
		StandardTokenizer standardTokenizer = new StandardTokenizer(stringReader);
		List<String> list = new ArrayList<>();
		standardTokenizer.reset();
		while(standardTokenizer.incrementToken()){
			CharTermAttribute attribute = standardTokenizer.getAttribute(CharTermAttribute.class);
			list.add(attribute.toString());
		}

		list.forEach(System.out::println);
	}

}
