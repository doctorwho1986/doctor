package com.github.jdk8.ebook.java8_recipes2nd_edition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Finding the Interval Between Dates and Times
 * 
 * @author doctor
 *
 * @since 2015年2月4日 上午12:08:14
 */
public class Chapter4Code {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LocalDate localDate1 = LocalDate.of(2010, 12, 1);
		LocalDate localDate2 = LocalDate.of(2015, 2, 4);
		Period period = Period.between(localDate1, localDate2);
		
		period.getUnits().forEach(System.out::println);
		System.out.println(period.getYears()+"years," + period.getMonths()+"months," + period.getDays()+"days");
		
		
		System.out.println(String.join(",", ChronoUnit.YEARS.between(localDate1, localDate2)+"years",
						  ChronoUnit.MONTHS.between(localDate1, localDate2)+"months",
						  ChronoUnit.DAYS.between(localDate1, localDate2)+"days"));
		System.out.println();

	}

}
