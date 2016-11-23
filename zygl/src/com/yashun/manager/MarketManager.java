package com.yashun.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yashun.bean.MarketMsgBean;
import com.yashun.bean.MessageBean;
import com.yashun.bean.UserBean;
import com.yashun.db.SqlHelper;
import com.yashun.form.MessageForm;
import com.yashun.service.MessageService;

public class MarketManager {

	
	//得到总记录数
		public int getCount(String id)
		{
			int pageCount=0;
			String sql="select count(*) from market where fp_user="+id;
			SqlHelper sqlhelper=new SqlHelper();
			ArrayList al=sqlhelper.excuteQuery(sql, null);
			Object[] obj=(Object[]) al.get(0);
			pageCount=Integer.parseInt(obj[0].toString());
			return pageCount;
		}
		//得到总页数数
		public int getPageCount(String id,int pageSize,int count)
		{
			return (count-1)/pageSize+1;
		}
	//分页查找
		public ArrayList getMessagesByPage(int pageNow,int pageSize,String id){
			    
			String sql="select * from market where fp_user="+id+" order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
			SqlHelper sqlHelper=new SqlHelper();
			ArrayList al=sqlHelper.excuteQuery(sql, null);
			ArrayList al2=new ArrayList();
			if(al.size()>0){
				for(int i=0;i<al.size();i++){
					Object []objs=(Object[]) al.get(i);
					MarketMsgBean message=new MarketMsgBean();
					message.setId(objs[0].toString());
					if(null!=objs[1])
						message.setKh_name(objs[1].toString());
					else
						message.setKh_name("");
					if(null!=objs[2])
						message.setKh_tel(objs[2].toString());
					else
						message.setKh_tel("空");
					if(null!=objs[3])
						message.setKh_address(objs[3].toString());
					else
						message.setKh_address("");
					if(null!=objs[4])
						message.setKh_ly(objs[4].toString());
					else
						message.setKh_ly("");
					message.setFp_user(objs[5].toString());
					if(null==objs[6])
						message.setFp_hf("");
					else
						message.setFp_hf(objs[6].toString());
					message.setIntime(objs[7].toString());
					if(null==objs[8])
						message.setLasthf("");
					else
						message.setLasthf(objs[8].toString());
					message.setType(objs[9].toString());
					if(null==objs[10])
						message.setTag("");
					else
						message.setTag(objs[10].toString());
					if(null==objs[11])
						message.setFirstinfo("");
					else
						message.setFirstinfo(objs[11].toString());
					al2.add(message);
				}
			}
			return al2;
		}
		
		public boolean checkByTel(String tel)
		{
			String sql = "select * from market where kh_tel=?";
			String[] params = {tel};
			SqlHelper sqlHelper = new SqlHelper();
			ArrayList al = sqlHelper.excuteQuery(sql, params);
			if(al.size()!=0)
				return true;
			return false;
		}
		//添加留言
		public boolean addMessage(MarketMsgBean mb)
		{
			String sql = "insert into market(kh_name,kh_tel,kh_address,kh_ly,fp_user,intime) values(?,?,?,?,?,?)";
			String[] params = {mb.getKh_name(),mb.getKh_tel(),mb.getKh_address(),mb.getKh_ly(),mb.getFp_user(),mb.getIntime()};
			SqlHelper sqlHelper = new SqlHelper();
			return sqlHelper.executeUpdate(sql, params);
		}
		
		//根据id获取留言
		public MarketMsgBean getMessageById(String id)
		{
			String sql = "select * from market where id = ?";
			String[] params = {id};
			SqlHelper sqlHelper = new SqlHelper();
			ArrayList al = sqlHelper.excuteQuery(sql, params);
			MarketMsgBean message=new MarketMsgBean();
			if(al.size()!= 0)
			{
				Object[] objs = (Object[])al.get(0);
				message.setId(objs[0].toString());
				if(null!=objs[1])
					message.setKh_name(objs[1].toString());
				else
					message.setKh_name("");
				if(null!=objs[2])
					message.setKh_tel(objs[2].toString());
				else
					message.setKh_tel("空");
				if(null!=objs[3])
					message.setKh_address(objs[3].toString());
				else
					message.setKh_address("");
				if(null!=objs[4])
					message.setKh_ly(objs[4].toString());
				else
					message.setKh_ly("");
				message.setFp_user(objs[5].toString());
				if(null==objs[6])
					message.setFp_hf("");
				else
					message.setFp_hf(objs[6].toString());
				message.setIntime(objs[7].toString());
				if(null==objs[8])
					message.setLasthf("");
				else
					message.setLasthf(objs[8].toString());
				message.setType(objs[9].toString());
				if(null==objs[10])
					message.setTag("");
				else
					message.setTag(objs[10].toString());
				if(null==objs[11])
					message.setFirstinfo("");
				else
					message.setFirstinfo(objs[11].toString());
			}
			return message;
		}
		//修改回访
		public boolean modifyHf(String id ,String hf,String lasthf,String hist)
		{
			String sql="update market set fp_hf=?,lasthf=?,firstinfo=? where id=?";
			String[] params = {hf,lasthf,hist,id};
			SqlHelper sqlHelper = new SqlHelper();
			return sqlHelper.executeUpdate(sql, params);
		}
		
		
		public ArrayList<MarketMsgBean> getMessageBySearch(String tel,String id,String name,String usernum)
		{
			String sql = "select * from market";
			if(tel!=null)
				sql+=" where kh_tel='"+tel+"'";
			else if(id!=null)
				sql+=" where id="+id;
			else if(name != null)
				sql+=" where kh_name='"+name+"'";
			if(usernum != null)
				sql+=" and fp_user = "+usernum;
			SqlHelper sqlHelper=new SqlHelper();
			ArrayList al=sqlHelper.excuteQuery(sql, null);
			ArrayList<MarketMsgBean> al2 = new ArrayList<MarketMsgBean>();
			if(al.size()>0){
				for(int i=0;i<al.size();i++){
					Object []objs=(Object[]) al.get(i);
					MarketMsgBean message=new MarketMsgBean();
					message.setId(objs[0].toString());
					if(null!=objs[1])
						message.setKh_name(objs[1].toString());
					else
						message.setKh_name("");
					if(null!=objs[2])
						message.setKh_tel(objs[2].toString());
					else
						message.setKh_tel("空");
					if(null!=objs[3])
						message.setKh_address(objs[3].toString());
					else
						message.setKh_address("");
					if(null!=objs[4])
						message.setKh_ly(objs[4].toString());
					else
						message.setKh_ly("");
					message.setFp_user(objs[5].toString());
					if(null==objs[6])
						message.setFp_hf("");
					else
						message.setFp_hf(objs[6].toString());
					message.setIntime(objs[7].toString());
					if(null==objs[8])
						message.setLasthf("");
					else
						message.setLasthf(objs[8].toString());
					message.setType(objs[9].toString());
					if(null==objs[10])
						message.setTag("");
					else
						message.setTag(objs[10].toString());
					if(null==objs[11])
						message.setFirstinfo("");
					else
						message.setFirstinfo(objs[11].toString());
					al2.add(message);
				}
			}
			return al2;
		}
}
