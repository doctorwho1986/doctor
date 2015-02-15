package com.github.jdk;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * @see http://howtodoinjava.com/2014/07/28/location-based-currency-formatting-in-java/
 * @author doctor
 *
 * @since 2015年2月15日 下午9:24:37
 */
public class LocationBasedCurrencyFormattingInJava {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Double doubleValue = Double.valueOf(123456789.555D);
		Locale locale = Locale.getDefault();
		Currency currency = Currency.getInstance(locale);
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		
		System.out.println("locale:" +locale.getDisplayName());
		System.out.println("Currency:" + currency.getDisplayName());
		System.out.println(numberFormat.format(doubleValue));
		
		System.out.println("Locale.FRANCE:" + NumberFormat.getCurrencyInstance(Locale.FRANCE).format(doubleValue));
	}

}
