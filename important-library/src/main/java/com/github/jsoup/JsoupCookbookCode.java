package com.github.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author doctor
 * @link http://www.open-open.com/jsoup/
 * 
 */
public class JsoupCookbookCode {
	private static final Logger log = LoggerFactory.getLogger(JsoupCookbookCode.class);
	
	@Test
	public void test_解析和遍历一个html文档() {
		String html = "<html><head><title>First parse</title></head>"
				+ "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document document = Jsoup.parse(html);
		log.info("{doc:{}}",document);
		
		Element body = document.body();
		log.info("{body:{}}",body);
		
		Elements elementsP = body.select("p");
		log.info("{{}}",elementsP);
		
		String text = elementsP.get(0).text();
		log.info("{{}}",text);
	}
}
