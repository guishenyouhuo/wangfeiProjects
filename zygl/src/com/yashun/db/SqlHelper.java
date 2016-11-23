package com.yashun.db;
import java.sql.*;
import java.util.*;
public class SqlHelper {

	//需要用到的对象
	PreparedStatement ps;
	Connection ct;
	ResultSet rs;
	
	//查询操作
	public ArrayList excuteQuery(String sql , String[] params){
		
		ArrayList al=new ArrayList();
		try {
			//连接数据库
			ct=ConnDb.getConn();
			ps=ct.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setString(i+1, params[i]);
				}
			}
			rs=ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int column=rsmd.getColumnCount();
			while(rs.next()){
				Object[] obj=new Object[column];
				for(int i=1;i<=column;i++){  //将所查询到的记录每列的值放到Object对象数组里面去
					obj[i-1]=rs.getObject(i);
				}
				al.add(obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			this.close();
		}
		return al;
	}
	
	//更新操作
	public boolean executeUpdate(String sql,String []params){
		boolean b=false;
		try {
			ct=ConnDb.getConn();
			ps=ct.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setString(i+1, params[i]);
				}
			}
			int rs=ps.executeUpdate();
			if(rs>0){
				b=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			b=false;
			// TODO: handle exception
		}finally{
			this.close();
		}
		return b ;
	}
	//关闭连接
	public void close(){
		
		if(rs!=null){
			try {
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(ct!=null){
			try {
				ct.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
