package com.blue.wappsender.core.formats;

public class DateFormatter {
	
	private static final String FORMAT = "yyyy-MM-dd HH:mm";
	private static final String INTERNAL_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String pattern() {
		return FORMAT;
	}
	
	public static String internalPattern() {
		return INTERNAL_FORMAT;
	}
}
