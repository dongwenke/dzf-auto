package com.dzf.test.util;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class PropertyUtil {

	public static Properties prop = getProperties();
	//配置基本路径
	public static final String BASEPATH = "config";
	
	//读取配置文件
	private static Properties getProperties() {
		Properties prop = new Properties();
		try {
			Reader reader = new InputStreamReader(new FileInputStream("config.properties"), "UTF-8");
			prop.load(reader);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}
