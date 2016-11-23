package com.yashun.service;

import java.util.ArrayList;

import com.yashun.bean.AdminBean;
import com.yashun.bean.UserBean;
import com.yashun.db.SqlHelper;

public class AdminService {
	public ArrayList<AdminBean> getAdmin()
	{
		ArrayList al = new ArrayList();
		ArrayList<AdminBean> al2 = new ArrayList<AdminBean>();
		String sql = "select * from admin order by id asc";
		SqlHelper sqlHelper = new SqlHelper();
		al = sqlHelper.excuteQuery(sql, null);
		if(al.size()>0){
			for(int i=0;i<al.size();i++){
				Object []objs=(Object[]) al.get(i);
				AdminBean mb = new AdminBean();
				mb.setId(objs[0].toString());
				mb.setName(objs[1].toString());
				mb.setPass(objs[2].toString());
				mb.setGs(objs[3].toString());
				mb.setType(objs[4].toString());
				al2.add(mb);
			}
		}
		return al2;
	}
	//ÐÞ¸Ä
	public boolean modify(AdminBean ab)
	{
		String sql = "update admin set adminname=?,adminpass=?,var_gs=? where id=?";
		String[] params={ab.getName(),ab.getPass(),ab.getGs(),ab.getId()};
		SqlHelper sqlHelper = new SqlHelper();
		if(sqlHelper.executeUpdate(sql, params))
		{
			return true;
		}
		return false;
	}

}
