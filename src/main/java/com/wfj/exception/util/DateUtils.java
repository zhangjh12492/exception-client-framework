package com.wfj.exception.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
	public static final String YMD = "yyyyMMdd";
	public static final String YYDDMMHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String YMD_SLASH = "yyyy/MM/dd";
	public static final String YMD_DASH = "yyyy-MM-dd";
	public static final String YMD_DASH_WITH_TIME = "yyyy-MM-dd H:m";
	public static final String YDM_SLASH = "yyyy/dd/MM";
	public static final String YDM_DASH = "yyyy-dd-MM";
	public static final String HM = "HHmm";
	public static final String HM_COLON = "HH:mm";
	public static final String YMDHMS = "yyyyMMddHHmmss";
	public static final String YMDHMSMIS = "yyyyMMddHHmmssSSS";
	public static final long DAY = 86400000L;
	private static final Map<String, DateFormat> DFS = new HashMap<String, DateFormat>();

	public static DateFormat getFormat(String pattern) {
		DateFormat format = (DateFormat) DFS.get(pattern);
		if (format == null) {
			format = new SimpleDateFormat(pattern);
			DFS.put(pattern, format);
		}
		return format;
	}

	public static Date parse(String source, String pattern) {
		if (source == null)
			return null;
		Date date;
		try {
			date = getFormat(pattern).parse(source);
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		return getFormat(pattern).format(date);
	}

	public static boolean isValid(int year, int month, int day) {
		if ((month > 0) && (month < 13) && (day > 0) && (day < 32)) {
			int mon = month - 1;
			Calendar calendar = new GregorianCalendar(year, mon, day);
			if ((calendar.get(1) == year) && (calendar.get(2) == mon) && (calendar.get(5) == day)) {
				return true;
			}
		}
		return false;
	}

	private static Calendar convert(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar;
	}

	public static Date yearOffset(Date date, int offset) {
		return offsetDate(date, 1, offset);
	}

	public static Date monthOffset(Date date, int offset) {
		return offsetDate(date, 2, offset);
	}

	public static Date dayOffset(Date date, int offset) {
		return offsetDate(date, 5, offset);
	}

	public static Date offsetDate(Date date, int field, int offset) {
		Calendar calendar = convert(date);
		calendar.add(field, offset);
		return calendar.getTime();
	}

	public static Date firstDay(Date date) {
		Calendar calendar = convert(date);
		calendar.set(5, 1);
		return calendar.getTime();
	}

	public static Date lastDay(Date date) {
		Calendar calendar = convert(date);
		calendar.set(5, calendar.getActualMaximum(5));
		return calendar.getTime();
	}

	public static int dayDiff(Date date1, Date date2) {
		long diff = date1.getTime() - date2.getTime();
		return (int) (diff / 86400000L);
	}

	public static String getCurrentDate() {
		return format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	public static String getDateYMDHMS() {
		return format(new Date(), "yyyyMMddHHmmss");
	}
	/**
	 * 获取日期到毫秒
	 * @return
	 */
	public static String getDateYMDHMSMIS() {
		return format(new Date(), "yyyyMMddHHmmssSSS");
	}

	public static Date parseReturnTime(String date, String pattern) {
		if (date == null) {
			return new Date();
		}
		return parse(date, pattern);
	}
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.util.DateUtils
 * JD-Core Version:    0.6.0
 */