package com.doctor.lucene.classes;

import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
/**
 * Chinese, Japanese, and Korean languages=CJK
 * @author doctor
 *
 * @time   2015年1月16日 上午9:38:18
 */
public class CJKAnalyzerPractice {

	public static void main(String[] args) throws Throwable {
		try (
				CJKAnalyzer cjkAnalyzer = new CJKAnalyzer();
				TokenStream tokenStream = cjkAnalyzer.tokenStream("myField", new StringReader("你好，博士，哪个博士，神秘博士"));) {
			CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				System.out.println("reflectAsString:" + tokenStream.reflectAsString(true));
				System.out.println("charTermAttribute:" + charTermAttribute.subSequence(0, charTermAttribute.length()));
				System.out.println("startOffset:" + offsetAttribute.startOffset());
				System.out.println("endOffset:" + offsetAttribute.endOffset());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
