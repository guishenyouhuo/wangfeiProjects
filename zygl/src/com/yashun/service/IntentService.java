package com.yashun.service;

import java.util.ArrayList;

import com.yashun.db.SqlHelper;

public class IntentService {
	public boolean checkExist(String messageid,String usernum)
	{
		String sql = "select * from intent where userid=? and messageid=? and flag =1";
		String[] params={usernum,messageid};
		SqlHelper sqlHelper = new SqlHelper();
		ArrayList al = sqlHelper.excuteQuery(sql, params);
		if(al.size()!=0)
			return true;
		return false;
	}
	public boolean InsertIntent(String messageid,String usernum)
	{
		String sql = "insert into intent(userid,messageid,flag) values(?,?,1)";
		String[] params={usernum,messageid};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeUpdate(sql, params);
	}
	public ArrayList getIntentByUser(String usernum)
	{
		String sql = "select messageid from intent where userid = ?";
		String[] params={usernum};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.excuteQuery(sql, params);
	}

}
