package com.bh.CouponProject3.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public static Date generateRandomDate(int year1, int year2) {
		return generateDateJava(randIntBetween(year1, year2), // year
				randIntBetween(1, 12), // month
				randIntBetween(1, 27)); // day
	}

	public static Date generateDateByYear(int year1) {
		return generateDateJava(year1, // year
				randIntBetween(1, 12), // month
				randIntBetween(1, 27)); // day
	}

	public static Date generateRandomDate() {
		return generateDateJava(randIntBetween(1960, 2022), // year
				randIntBetween(1, 12), // month
				randIntBetween(1, 27)); // day
	}

	public static java.util.Date generateDateJava(int year, int month, int day) { // Moti
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1); // 0 = January, 11 = December
		calendar.set(Calendar.DAY_OF_MONTH, day); // 1 to 31
		return calendar.getTime();
	}

	public static int randIntBetween(int a, int b) {
		return (int) (Math.random() * (b - a) + a);
	}

}
