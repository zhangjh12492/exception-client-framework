package com.wfj.exception.util;

import java.util.Properties;

public class PropertiesLoad {
	private static Properties pros = new Properties();

	public static void init(Properties pro) {
		pros = pro;
	}

	public static String getProperties(String key) {
		return pros.getProperty(key);
	}

	public static void putProperties(String key, String value) {
		pros.put(key, value);
	}
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.util.PropertiesLoad
 * JD-Core Version:    0.6.0
 */