package com.yashun.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class TestreadFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream("src/from.properties");
			prop.load(fis);
			Set<Object> s = prop.keySet();
			for(Object o : s)
			{
				System.out.println(o.toString());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
