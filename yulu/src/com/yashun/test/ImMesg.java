package com.yashun.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.yashun.bean.MessageBean;
import com.yashun.bean.UserBean;
import com.yashun.db.ConAcess;
import com.yashun.db.SqlHelper;
import com.yashun.manager.AdminManager;

public class ImMesg {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn =ConAcess.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from khmessage");
			ResultSet re = ps.executeQuery();
			ArrayList<MessageBean> al = new ArrayList<MessageBean>();
			AdminManager adminManager =new AdminManager();
			while(re.next())
			{
				MessageBean mb =new MessageBean();
				String name = re.getString(2);
				String tel = re.getString(3);
				String address = re.getString(4);
				String ly = re.getString(5);
				int fp = re.getInt(6);
				String lhf = re.getString(7);
				String intime = re.getString(8);
				String lasthftime = re.getString(9);
//				String type = re.getString(10);
//				String tag = re.getString(11);
//				String last_user = re.getString(12);
				MessageBean km = new MessageBean();
				km.setKh_name(name);
				km.setKh_tel(tel);
				km.setKh_address(address);
				km.setKh_ly(ly);
				km.setFp_user(fp+"");
				km.setFp_hf(lhf);
				km.setIntime(intime);
				if(lasthftime==null||"".equals(lasthftime))
					km.setLasthf(null);
				else
				{
					km.setLasthf(lasthftime);
				}
				
					km.setIntime(intime);
					//km.setLast_user(last_user)
					km.setType(1+"");
					km.setTag("");
//				}
					//adminManager.addMessage(km);
					String sql = "insert into khmessage(kh_name,kh_tel,kh_address,kh_ly,fp_hf,fp_user,intime,tag,lasthf) values(?,?,?,?,?,?,?,?,?)";
					String[] params = {km.getKh_name(),km.getKh_tel(),km.getKh_address(),km.getKh_ly(),km.getFp_hf(),km.getFp_user(),km.getIntime(),km.getTag(),km.getLasthf()};
					SqlHelper sqlHelper = new SqlHelper();
					sqlHelper.executeUpdate(sql, params);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
