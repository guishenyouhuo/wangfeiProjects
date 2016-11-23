package com.yashun.db;
import java.sql.*;
import java.util.*;
public class SqlHelper {

	//��Ҫ�õ��Ķ���
	PreparedStatement ps;
	Connection ct;
	ResultSet rs;
	
	//��ѯ����
	public ArrayList excuteQuery(String sql , String[] params){
		
		ArrayList al=new ArrayList();
		try {
			//�������ݿ�
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
				for(int i=1;i<=column;i++){  //������ѯ���ļ�¼ÿ�е�ֵ�ŵ�Object������������ȥ
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
	
	//���²���
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
	//�ر�����
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
