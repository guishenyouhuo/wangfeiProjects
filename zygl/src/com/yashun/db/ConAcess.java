package com.yashun.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConAcess {
	private static Connection conn = null;
	public static Connection getConn(){
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=d://#mdb2012.mdb";
			conn = DriverManager.getConnection(url,"","");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
