package com.panfeng.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author dawn 常量类
 */
public final class Constants {

	public static String TEMP_DIR;
	public static String OFFICEHOME;
	public static String PDF2HTML;
	public static String DOC_DIR;

	private static Constants constants=new Constants();
	
	private Constants() {
		load();
	}

	public static Constants getConstants(){
		return Constants.constants;
	}
	public void reLoadConstants() {
		load();
	}
	
	private static void load() {
		InputStream is = Constants.class.getClassLoader().getResourceAsStream(
				"jdbc.properties");
		try {
			Properties propertis = new Properties();
			propertis.load(is);
			full(propertis);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	private static void full(Properties properties) {
		TEMP_DIR = properties.getProperty("temp.dir");
		OFFICEHOME = properties.getProperty("officehome");
		PDF2HTML = properties.getProperty("pdf2html");
		DOC_DIR = properties.getProperty("doc.dir");
	}
}
