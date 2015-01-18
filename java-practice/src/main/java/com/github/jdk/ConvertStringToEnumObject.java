package com.github.jdk;

/**
 * @see http://www.mkyong.com/java/java-convert-string-to-enum-object/
 * @author doctor
 *
 * @since 2015年1月18日 上午10:52:47
 */
public class ConvertStringToEnumObject {

	public static void main(String[] args) {
		System.out.println(WhoisRIR.valueOf("arin".toUpperCase()));
		System.out.println(WhoisRIR.valueOf("UNKNOWN".toUpperCase()));
		
		for (WhoisRIR item : WhoisRIR.values()) {
			System.out.println(item.toString());
		}

	}
	
	private static enum WhoisRIR{
		ARIN("whois.arin.net"),
		RIPE("whois.ripe.net"),
		APNIC("whois.apnic.net"),
		AFRINIC("whois.afrinic.net"), 
		LACNIC("whois.lacnic.net"),
		JPNIC("whois.nic.ad.jp"),
		KRNIC("whois.nic.or.kr"),
		CNNIC("ipwhois.cnnic.cn"),
		UNKNOWN("");
		private String url;
		private WhoisRIR(String url){
			this.url = url;
		}
		public String getUrl() {
			return url;
		}
		
	}

}
