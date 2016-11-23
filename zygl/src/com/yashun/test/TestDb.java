package com.yashun.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yashun.bean.UserBean;
import com.yashun.db.ConAcess;
import com.yashun.db.ConnDb;
import com.yashun.manager.AdminManager;
import com.yashun.manager.UserManager;

public class TestDb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Connection conn =ConAcess.getConn();
		Connection conn = ConnDb.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from gs_user where gs_name='уе╨Л'");
			ResultSet re = ps.executeQuery();
			ArrayList<UserBean> al = new ArrayList<UserBean>();
			AdminManager adminManager =new AdminManager();
			while(re.next())
			{
				System.out.println(re.getString(2));
//				UserBean ub =new UserBean();
//				ub.setGs_name(re.getString(2));
//				ub.setGs_num(re.getString(3));
//				ub.setPws(re.getString(4));
//				ub.setTy_flag(1);
//				al.add(ub);
			}
//			for(UserBean ub:al)
//			{
//				adminManager.addUser(ub);
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
