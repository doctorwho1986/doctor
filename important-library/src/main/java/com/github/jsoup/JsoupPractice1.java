package com.github.jsoup;

import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author 
 * http://www.fjtc.com.cn/NewsList-1105
 */
public class JsoupPractice1 {
	private static final Logger log = LoggerFactory.getLogger(JsoupPractice1.class);
	private static final String JLC_OPEN_AWARD_URL = "http://www.fjtc.com.cn/NewsList-1105";
	
	public static void main(String[] args) {
		try {
			final URL url = new URL(JLC_OPEN_AWARD_URL);
			Document document = Jsoup.parse(url, 20000);
			Elements elements = document.select("table").select("tr").get(1).select("td");
			StringBuffer resultNo = new StringBuffer(14);
			StringBuilder currentIssueNo = new StringBuilder(8);
			
			currentIssueNo.append(elements.get(1).text().trim());
			
			Elements elementsNo = elements.get(3).select("span");
			for (Element element : elementsNo) {
				resultNo.append(element.text().trim()).append("|");
			}
			resultNo.deleteCharAt(resultNo.length()-1);
			
			
			log.info(currentIssueNo.toString());
			log.info(resultNo.toString());
			log.info(elementsNo.toString());
		} catch (Throwable t) {
			String message = "{msg:'获取福建11选5开奖结果失败"+JLC_OPEN_AWARD_URL +"}";
			log.error(message, t);
		}

	}

}
