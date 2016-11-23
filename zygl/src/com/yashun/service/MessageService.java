package com.yashun.service;

import java.util.ArrayList;
import java.util.List;

import com.yashun.bean.MessageBean;
import com.yashun.bean.UserBean;
import com.yashun.db.SqlHelper;

public class MessageService {
	//得到总记录数
	public int getCount(String id,String type)
	{
		int pageCount=0;
		String sql;
		if(id!=null)
		{
			sql="select count(*) from khmessage where fp_user="+id+" and type!=3";
			if(type!=null)
				sql+=" and type="+type;
		}
		else
			sql="select count(*) from khmessage where type!=3";
		SqlHelper sqlhelper=new SqlHelper();
		ArrayList al=sqlhelper.excuteQuery(sql, null);
		Object[] obj=(Object[]) al.get(0);
		pageCount=Integer.parseInt(obj[0].toString());
		return pageCount;
	}
	//得到总页数，限定id
	public int getPageCountByid(int pageSize,String id,String type)
	{
		int pageCount=0;
		String sql;
		if(id==null)
			sql="select count(*) from khmessage where type!=3";
		else
		{
			sql="select count(*) from khmessage where fp_user ="+id+" and type!=3";
			if(type!=null)
				sql+=" and type="+type;
		}
		SqlHelper sqlhelper=new SqlHelper();
		ArrayList al=sqlhelper.excuteQuery(sql, null);
		Object[] obj=(Object[]) al.get(0);
		pageCount=Integer.parseInt(obj[0].toString());
		return (pageCount-1)/pageSize+1;
	}
	//得到总页数
	public int getPageCount(int pageSize)
	{
		int pageCount=0;
		String sql="select count(*) from khmessage";
		SqlHelper sqlhelper=new SqlHelper();
		ArrayList al=sqlhelper.excuteQuery(sql, null);
		Object[] obj=(Object[]) al.get(0);
		pageCount=Integer.parseInt(obj[0].toString());
		return (pageCount-1)/pageSize+1;
	}
	//分页查找 限定id
		public ArrayList getMessagesByPageById(int pageNow,int pageSize,String id,String type){

			String sql;
			if(pageNow ==1)
			{
				if(id==null)
					sql = "select * from khmessage where type!=3  order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
				else
				{
					sql = "select * from khmessage where fp_user="+id+" and type!=3  order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
					if(type!=null)
						sql = "select * from khmessage where fp_user="+id+" and type="+type+"  order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
				}
			}
			else
				if(id==null)
					sql = "select * from khmessage where type!=3 order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
				else
				{
					sql="select * from khmessage where fp_user="+id+" and type!=3 order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
					if(type!=null)
						sql="select * from khmessage  where fp_user="+id+" and type="+type+" order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
				}
			SqlHelper sqlHelper=new SqlHelper();
			ArrayList al=sqlHelper.excuteQuery(sql, null);
			ArrayList al2=new ArrayList();
			if(al.size()>0){
				for(int i=0;i<al.size();i++){
					Object []objs=(Object[]) al.get(i);
					MessageBean message=new MessageBean();
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
						message.setLast_user("");
					else
						message.setLast_user(objs[11].toString());
					al2.add(message);
				}
			}
			return al2;
		}
		/**
		 * 已完成客户
		 */
		public int getFinishCount(String id,String type)
		{
			int pageCount=0;
			String sql;
			if(id!=null)
			{
				sql="select count(*) from khmessage where fp_user="+id+" and type="+type;
			}
			else
				sql="select count(*) from khmessage where type="+type;
			SqlHelper sqlhelper=new SqlHelper();
			ArrayList al=sqlhelper.excuteQuery(sql, null);
			Object[] obj=(Object[]) al.get(0);
			pageCount=Integer.parseInt(obj[0].toString());
			return pageCount;
		}
		public int getCompletePageCountByid(int pageSize,String id,String type)
		{
			int pageCount=0;
			String sql;
			if(id==null)
				sql="select count(*) from khmessage where type="+type;
			else
			{
				sql="select count(*) from khmessage where fp_user ="+id+" and type="+type;
			}
			SqlHelper sqlhelper=new SqlHelper();
			ArrayList al=sqlhelper.excuteQuery(sql, null);
			Object[] obj=(Object[]) al.get(0);
			pageCount=Integer.parseInt(obj[0].toString());
			return (pageCount-1)/pageSize+1;
		}
		//分页查找 限定id
				public ArrayList getCompletedByPageById(int pageNow,int pageSize,String id,String type){

					String sql;
					if(pageNow ==1)
					{
						if(id==null)
							sql = "select * from khmessage where type="+type+"  order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
						else
						{
							sql = "select * from khmessage where fp_user="+id+" and type="+type+"  order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
						}
					}
					else
						if(id==null)
							sql = "select * from khmessage where type="+type+" order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
						else
						{
							sql="select * from khmessage where fp_user="+id+" and type="+type+" order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
						}
					SqlHelper sqlHelper=new SqlHelper();
					ArrayList al=sqlHelper.excuteQuery(sql, null);
					ArrayList al2=new ArrayList();
					if(al.size()>0){
						for(int i=0;i<al.size();i++){
							Object []objs=(Object[]) al.get(i);
							MessageBean message=new MessageBean();
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
								message.setLast_user("");
							else
								message.setLast_user(objs[11].toString());
							al2.add(message);
						}
					}
					return al2;
				}
		
		
	//分页查找
	public ArrayList getMessagesByPage(int pageNow,int pageSize){
		    
		String sql="select * from khmessage order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
		SqlHelper sqlHelper=new SqlHelper();
		ArrayList al=sqlHelper.excuteQuery(sql, null);
		ArrayList al2=new ArrayList();
		if(al.size()>0){
			for(int i=0;i<al.size();i++){
				Object []objs=(Object[]) al.get(i);
				MessageBean message=new MessageBean();
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
					message.setLast_user("");
				else
					message.setLast_user(objs[11].toString());
				al2.add(message);
			}
		}
		return al2;
	}
	public UserBean getUserById(String num)
	{
		UserBean ub = new UserBean();
		String sql = "select * from gs_user where gs_num=?";
		String[] params = {num};
		SqlHelper helper = new SqlHelper();
		ArrayList al = helper.excuteQuery(sql, params);
		if(al.size()!= 0)
		{
			Object[] objs = (Object[])al.get(0);
			ub.setId(objs[0].toString());
			ub.setGs_name(objs[1].toString());
			ub.setGs_num(objs[2].toString());
			ub.setPws(objs[3].toString());
			ub.setTy_flag(Integer.parseInt(objs[4].toString()));
		}
		return ub;
	}
	public ArrayList getAllUser()
	{
		String sql="select * from gs_user order by gs_num asc";
		SqlHelper sqlHelper=new SqlHelper();
		ArrayList al=sqlHelper.excuteQuery(sql, null);
		ArrayList al2=new ArrayList();
		for(int i=0;i<al.size();i++){
			Object[] objs=(Object[]) al.get(i);
			UserBean user=new UserBean();
			user.setId(objs[0].toString());
			user.setGs_name(objs[1].toString());
			user.setGs_num(objs[2].toString());
			user.setPws(objs[3].toString());
			user.setTy_flag(Integer.parseInt(objs[4].toString()));
			al2.add(user);
		}
		return al2;
	}
	//将留言分配给指定的人
	public boolean moveLy(String id,String user_num,String last_num)
	{
		String sql = "update khmessage set fp_user = ?,last_user=?  where id = ?";
		String[] params = {user_num,last_num,id};
		SqlHelper helper = new SqlHelper();
		if(helper.executeUpdate(sql, params))
		{
			return true;
		}
		else
			return false;
	}
	//删除留言
	public boolean deleteMessage(String id)
	{
		String sql = "delete from khmessage where id = ?";
		String[] params = {id};
		SqlHelper sqlHelper = new SqlHelper();
		if(sqlHelper.executeUpdate(sql, params))
			return true;
		return false;
	}
	//添加留言
	public boolean addMessage(MessageBean mb)
	{
		String sql = "insert into khmessage(kh_name,kh_tel,kh_address,kh_ly,fp_user,intime,tag) values(?,?,?,?,?,?,?)";
		String[] params = {mb.getKh_name(),mb.getKh_tel(),mb.getKh_address(),mb.getKh_ly(),mb.getFp_user(),mb.getIntime(),mb.getTag()};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeUpdate(sql, params);
	}
	//根据id获取留言
	public MessageBean getMessageById(String id)
	{
		String sql = "select * from khmessage where id = ?";
		String[] params = {id};
		SqlHelper sqlHelper = new SqlHelper();
		ArrayList al = sqlHelper.excuteQuery(sql, params);
		MessageBean message=new MessageBean();
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
				message.setLast_user("");
			else
				message.setLast_user(objs[11].toString());
			message.setType(objs[9].toString());
		}
		return message;
	}
	//修改回访
	public boolean modifyHf(String id ,String hf,String lasthf,String flag)
	{
		String sql = "";
		if("0".equals(flag))
		{
			sql = "update khmessage set fp_hf=?,lasthf=?,type=1 where id=?";
		} 
		else
			sql="update khmessage set fp_hf=?,lasthf=? where id=?";
		String[] params = {hf,lasthf,id};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeUpdate(sql, params);
	}
	/**
	 * 任务查询
	 */
	//获取总记录数
	public int getCountByTime(String id,String time)
	{
		int pageCount=0;
		String sql = "select count(*) from khmessage where fp_user="+id+" and lasthf like '"+time+"%'";
		SqlHelper sqlhelper=new SqlHelper();
		ArrayList al=sqlhelper.excuteQuery(sql, null);
		Object[] obj=(Object[]) al.get(0);
		pageCount=Integer.parseInt(obj[0].toString());
		return pageCount;
	}
	//得到总页数
	public int getPageCountByTime(int pageSize,String id,String time)
	{
		int pageCount=0;
		String sql = "select count(*) from khmessage where fp_user ="+id+" and lasthf like '"+time+"%'";
		SqlHelper sqlhelper=new SqlHelper();
		ArrayList al=sqlhelper.excuteQuery(sql, null);
		Object[] obj=(Object[]) al.get(0);
		pageCount=Integer.parseInt(obj[0].toString());
		return (pageCount-1)/pageSize+1;
	}
	//根据下次回访时间分页查询
	public ArrayList<MessageBean> getMessagesByTime(String time,String id,int pageNow,int pageSize)
	{
		String sql;
//		if(pageNow ==1)
//		{
				sql = "select * from khmessage  where fp_user="+id+" and lasthf like '"+time+"%' order by id desc limit "+(pageNow-1)*pageSize+","+pageSize;
//		}
//		else		
//			   sql="select * from khmessage limit "+(pageNow-1)*pageSize+","+pageSize+" where fp_user="+id+" and lasthf like '"+time+"%' order by id desc";
			SqlHelper sqlHelper=new SqlHelper();
			ArrayList al=sqlHelper.excuteQuery(sql, null);
			ArrayList<MessageBean> al2=new ArrayList<MessageBean>();
			if(al.size()>0){
				for(int i=0;i<al.size();i++){
					Object []objs=(Object[]) al.get(i);
					MessageBean message=new MessageBean();
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
						message.setLast_user("");
					else
						message.setLast_user(objs[11].toString());
					al2.add(message);
				}
			}
			return al2;
	}
	public ArrayList<MessageBean> getMessageBySearch(String tel,String id,String name,String usernum)
	{
		String sql = "select * from khmessage";
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
		ArrayList<MessageBean> al2 = new ArrayList<MessageBean>();
		if(al.size()>0){
			for(int i=0;i<al.size();i++){
				Object []objs=(Object[]) al.get(i);
				MessageBean message=new MessageBean();
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
					message.setLast_user("");
				else
					message.setLast_user(objs[11].toString());
				al2.add(message);
			}
		}
		return al2;
	}
	public boolean intent(String id)
	{
		String sql = "update khmessage set type=2 where id=?";
		String[] params = {id};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeUpdate(sql, params);
	}
	public boolean outIntent(String id)
	{
		String sql = "update khmessage set type=1 where id=?";
		String[] params = {id};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeUpdate(sql, params);
	}
	public boolean checkByTel(String tel)
	{
		String sql = "select * from khmessage where kh_tel=?";
		String[] params = {tel};
		SqlHelper sqlHelper = new SqlHelper();
		ArrayList al = sqlHelper.excuteQuery(sql, params);
		if(al.size()!=0)
			return true;
		return false;
	}
	public String getAutolyNum()
	{
		String sql = "select nownum from autoly";
		SqlHelper sqlHelper = new SqlHelper();
		List al = sqlHelper.excuteQuery(sql, null);
		Object[] objs = (Object[])al.get(0);
		return objs[0].toString();
	}
	public boolean updateNowNum(String nowNum)
	{
		String sql = "update autoly set nownum=?";
		String[] params={nowNum};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeUpdate(sql, params);
	}
	public boolean updateType(String type,String id)
	{
		String sql = "update khmessage set type=? where id=?";
		String[] params={type,id};
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeUpdate(sql, params);
	}
	
	public List<String> getLinkDataList(String str,String uid)
	{
		String sql = "select kh_tel from khmessage where kh_tel like '"+str+"%' and fp_user=?";
		String[] params={uid};
		SqlHelper sqlHelper = new SqlHelper();
		List<String> linkList = new ArrayList<String>(); 
		List list = sqlHelper.excuteQuery(sql, params);
		for(int i =0;i<list.size();i++)
		{
			Object[] obj = (Object[])list.get(i);
			String tel = obj[0].toString();
			linkList.add(tel);
		}
		return linkList;
	}
}
