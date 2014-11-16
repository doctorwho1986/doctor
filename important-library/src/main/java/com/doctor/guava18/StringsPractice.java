package com.doctor.guava18;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
/**
 * 
 * @author doctor
 *
 * @since 2014年11月16日 下午3:34:48
 */
public class StringsPractice {

	public static void main(String[] args) {
		System.out.println(Strings.isNullOrEmpty(""));
		System.out.println(Strings.isNullOrEmpty(null));
		System.out.println(Strings.commonPrefix("abc", "abccddfdf"));
		System.out.println(Strings.commonSuffix("whodjfname", "djfkdname"));
		System.out.println(Strings.repeat("who", 3));
		System.out.println(Strings.padEnd("ss", 3, 'w'));
		
		System.out.println();
		System.out.println(CharMatcher.JAVA_DIGIT.collapseFrom("s84/;8", ' '));

	}

}
