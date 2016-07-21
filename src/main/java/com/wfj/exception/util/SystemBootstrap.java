package com.wfj.exception.util;

import java.io.InputStream;
import java.util.Properties;

public class SystemBootstrap {
	private static final String CONFIG_FILE_PATH = "/errorPro.properties";

	public static void afterPropertiesSet() {
		InputStream inputStream = null;
		Properties properties = new Properties();
		try {
			inputStream = SystemBootstrap.class.getResourceAsStream("/errorPro.properties");
			properties.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PropertiesLoad.init(properties);
	}
}

/* Location:           E:\EclipseEE\test_project\exception-client-framework\target\classes\
 * Qualified Name:     com.wfj.exception.util.SystemBootstrap
 * JD-Core Version:    0.6.0
 */