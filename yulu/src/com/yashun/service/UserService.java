package com.yashun.service;

import java.util.ArrayList;

import com.yashun.bean.MessageBean;
import com.yashun.bean.UserBean;
import com.yashun.db.SqlHelper;

public class UserService {
	public ArrayList getAllUsers()
	{
		ArrayList al = new ArrayList();
		ArrayList al2 = new ArrayList();
		String sql = "select * from gs_user order by gs_num asc";
		SqlHelper sqlHelper = new SqlHelper();
		al = sqlHelper.excuteQuery(sql, null);
		if(al.size()>0){
			for(int i=0;i<al.size();i++){
				Object []objs=(Object[]) al.get(i);
				UserBean ub = new UserBean();
				ub.setId(objs[0].toString());
				ub.setGs_name(objs[1].toString());
				ub.setGs_num(objs[2].toString());
				ub.setPws(objs[3].toString());
				ub.setTy_flag(Integer.parseInt(objs[4].toString()));
				al2.add(ub);
			}
		}
		return al2;
	}
	
	public ArrayList getAllOpenUsers()
	{
		ArrayList al = new ArrayList();
		ArrayList al2 = new ArrayList();
		String sql = "select * from gs_user where ty_flag = 1 order by gs_num asc";
		SqlHelper sqlHelper = new SqlHelper();
		al = sqlHelper.excuteQuery(sql, null);
		if(al.size()>0){
			for(int i=0;i<al.size();i++){
				Object []objs=(Object[]) al.get(i);
				UserBean ub = new UserBean();
				ub.setId(objs[0].toString());
				ub.setGs_name(objs[1].toString());
				ub.setGs_num(objs[2].toString());
				ub.setPws(objs[3].toString());
				ub.setTy_flag(Integer.parseInt(objs[4].toString()));
				al2.add(ub);
			}
		}
		return al2;
	}
	
	public boolean openUser(String id)
	{
		String sql = "update gs_user set ty_flag=1 where id = ?";
		String params[] = {id};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeUpdate(sql, params);
	}
	public boolean closeUser(String id)
	{
		String sql = "update gs_user set ty_flag=0 where id = ?";
		String params[] = {id};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeUpdate(sql, params);
	}
	public UserBean getUserById(String userid)
	{
		String sql = "select * from gs_user where id = ?";
		String[] params={userid};
		SqlHelper sqlHelper = new SqlHelper();
		ArrayList al =sqlHelper.excuteQuery(sql, params);
		UserBean ub = new UserBean();
		if(al.size()!=0)
		{
			Object[] objs = (Object[])al.get(0);
			ub.setId(objs[0].toString());
			ub.setGs_name(objs[1].toString());
			ub.setGs_num(objs[2].toString());
			ub.setPws(objs[3].toString());
		}
		return ub;
	}
	public UserBean getUserByNum(String num)
	{
		String sql = "select * from gs_user where gs_num = ?";
		String[] params={num};
		SqlHelper sqlHelper = new SqlHelper();
		ArrayList al =sqlHelper.excuteQuery(sql, params);
		UserBean ub = new UserBean();
		if(al.size()!=0)
		{
			Object[] objs = (Object[])al.get(0);
			ub.setId(objs[0].toString());
			ub.setGs_name(objs[1].toString());
			ub.setGs_num(objs[2].toString());
			ub.setPws(objs[3].toString());
		}
		return ub;
	}
	public boolean updateUserById(String id,String[] params)
	{
		String sql = "update gs_user set gs_name=?,gs_num=?,pws=? where id=?";
		String[] sparams={params[0],params[1],params[2],id};
		SqlHelper sqlHelper = new SqlHelper();
		if(sqlHelper.executeUpdate(sql, sparams))
		{
			return true;
		}
		return false;
	}
	//add
	public boolean addUser(UserBean ub)
	{
		String sql = "insert into gs_user(gs_name,gs_num,pws,ty_flag) values(?,?,?,?)";
		String[] params={ub.getGs_name(),ub.getGs_num(),ub.getPws(),ub.getTy_flag()+""};
		SqlHelper sqlHelper = new SqlHelper();
		if(sqlHelper.executeUpdate(sql, params))
		{
			return true;
		}
		return false;
	}
	//¸ÄÃÜÂë
	public boolean modifyPass(String pws,String id)
	{
		String sql = "update gs_user set pws = ? where id = ?";
		String[] params = {pws,id};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeUpdate(sql, params);
	}
	//É¾³ý
	public boolean delUser(String id)
	{
		String sql = "delete from gs_user where id = ?";
		String[] params = {id};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeUpdate(sql, params);
	}

}
