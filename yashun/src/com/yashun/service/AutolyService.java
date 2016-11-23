package com.yashun.service;

import java.util.ArrayList;

import com.yashun.bean.AutoBean;
import com.yashun.db.SqlHelper;

public class AutolyService {
	public AutoBean getAuto()
	{
		String sql = "select * from autoly where id =1";
		SqlHelper sqlHelper = new SqlHelper();
		AutoBean ab = new AutoBean();
		ArrayList al = sqlHelper.excuteQuery(sql, null);
		if(al.size()>0)
		{
			Object[] objs = (Object[])al.get(0);
			ab.setId(objs[0].toString());
			ab.setMaxuser(objs[1].toString());
			ab.setNownum(objs[2].toString());
		}
		return ab;
	}
	public boolean updateAuto(String maxuser,String nownum)
	{
		String sql = "update autoly set maxuser=? , nownum=? where id =1";
		String[] params = {maxuser,nownum};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeUpdate(sql, params);
	}

}
