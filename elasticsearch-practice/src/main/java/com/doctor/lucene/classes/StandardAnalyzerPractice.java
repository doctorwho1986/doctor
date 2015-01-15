package com.doctor.lucene.classes;

import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author doctor
 *
 * @time   2015年1月15日 下午4:43:13
 */
public class StandardAnalyzerPractice {
	private static final Logger log = LoggerFactory.getLogger(StandardAnalyzerPractice.class);
	
	public static void main(String[] args) {
		try (
				StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
				TokenStream tokenStream = standardAnalyzer.tokenStream("myField", new StringReader("hello doctor,how are you"));

		) {
			standardAnalyzer.setVersion(Version.LUCENE_4_10_3);
			OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
			CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			tokenStream.reset();//Resets this stream to the beginning. (Required)
			while(tokenStream.incrementToken()){
				 // Use {@link org.apache.lucene.util.AttributeSource#reflectAsString(boolean)}
		        // for token stream debugging.
				System.out.println("token:"+tokenStream.reflectAsString(true));
				System.out.println("content:" + charTermAttribute.subSequence(0, charTermAttribute.length()));
				System.out.println("token start:" + offsetAttribute.startOffset());
				System.out.println("token end:" + offsetAttribute.endOffset());
			}
			tokenStream.end();
		} catch (Exception e) {
			log.error("", e);
		}

		
		
		//中文呢
		try (
				StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
				TokenStream tokenStream = standardAnalyzer.tokenStream("myField", new StringReader("神秘 博士"));

		) {
			standardAnalyzer.setVersion(Version.LUCENE_4_10_3);
			OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
			CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			tokenStream.reset();//Resets this stream to the beginning. (Required)
			while(tokenStream.incrementToken()){
				 // Use {@link org.apache.lucene.util.AttributeSource#reflectAsString(boolean)}
		        // for token stream debugging.
				System.out.println("token:"+tokenStream.reflectAsString(true));
				System.out.println("content:" + charTermAttribute.subSequence(0, charTermAttribute.length()));
				System.out.println("token start:" + offsetAttribute.startOffset());
				System.out.println("token end:" + offsetAttribute.endOffset());
			}
			tokenStream.end();
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
