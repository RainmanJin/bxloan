package com.coamctech.bxloan.commons;

import java.util.Properties;

import com.coamctech.bxloan.commons.utils.Utils;


public class PropertiesConstants {
	
	private static Properties properties = null;
	
	public static String getProperty(String key) {
		if (properties == null) {
			properties = Utils.loadPropertiesFileFromClassPath("application.properties");
		}
		return properties.getProperty(key);
	}
}
