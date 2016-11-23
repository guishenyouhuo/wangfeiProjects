package com.yashun.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class ManProperties {
	private static Properties prop = new Properties();
	@SuppressWarnings("finally")
	public static List<String> getTags(String realPath)
	{
		List<String> tags = new ArrayList<String>();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(realPath);
			prop.load(fis);
			Set<Object> s = prop.keySet();
			for(Object o : s)
				tags.add(o.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(fis!=null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		return tags;
	}
	}

	public static void addProperties(String key,String value,String realPath)
	{
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(realPath);
			prop.setProperty(key, value);
			prop.store(fos, key+value);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(fos!=null)
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	}
	public static String getTemplate(String key)
	{
		return prop.getProperty(key);
	}
}
