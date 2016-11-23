package com.yashun.service;

import java.util.ArrayList;

import com.yashun.db.SqlHelper;
import com.yashun.form.AdLoginForm;
import com.yashun.form.UserForm;

public class LoginService {
	public boolean checkAdLogin(AdLoginForm alf)
	{
		String sql = "select adminpass from admin where adminname = ? and type ='1'";
		String[] params = {alf.getAdminname()};
		SqlHelper sqlHelper = new SqlHelper();
		@SuppressWarnings("rawtypes")
		ArrayList al =  sqlHelper.excuteQuery(sql, params);
		if(al.size()>0)
		{
			Object[] obj = (Object[])al.get(0);
			String addminpass = (String)obj[0];
			if(alf.getAdminpass().equals(addminpass))
				return true;
		}
		return false;
	}
	public boolean checkUserLogin(UserForm uf)
	{
		String sql = "select pws from gs_user where gs_name = ?";
		String[] params = {uf.getGs_name()};
		SqlHelper sqlHelper = new SqlHelper();
		ArrayList al =  sqlHelper.excuteQuery(sql, params);
		if(al.size()>0)
		{
			Object[] obj = (Object[])al.get(0);
			String pass = (String)obj[0];
			if(uf.getPassword().equals(pass))
				return true;
		}
		return false;
	}
	public String getGs_num(UserForm uf)
	{
		String sql = "select gs_num from gs_user where gs_name = ?";
		String[] params = {uf.getGs_name()};
		SqlHelper sqlHelper = new SqlHelper();
		ArrayList al =  sqlHelper.excuteQuery(sql, params);
		if(al.size()!=0)
		{
			Object[] obj = (Object[])al.get(0);
			return obj[0].toString();
		}
		else
			return null;
	}
	
}
