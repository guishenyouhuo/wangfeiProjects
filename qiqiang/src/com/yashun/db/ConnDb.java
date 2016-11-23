package com.yashun.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnDb {
	
	private static Connection ct;
	private static String Driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/qiqiang";
	private static String user="qiqiang";
	private static String password="w4762681";
	public static Connection getConn(){
		
		try {
			//加载驱动
			Class.forName(Driver);
			//得到连接
			ct=DriverManager.getConnection(url,user,password);
			return ct;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}
		
	}
}
