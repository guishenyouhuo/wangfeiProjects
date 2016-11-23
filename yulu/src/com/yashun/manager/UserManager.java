package com.yashun.manager;

import java.util.ArrayList;

import com.yashun.bean.MessageBean;
import com.yashun.bean.UserBean;
import com.yashun.form.UserForm;
import com.yashun.service.IntentService;
import com.yashun.service.LoginService;
import com.yashun.service.MessageService;
import com.yashun.service.UserService;

public class UserManager {
	//检查用户登录
		public boolean checkLogin(UserForm uf)
		{
			LoginService ls = new LoginService();
			return ls.checkUserLogin(uf);
		}
		public String getGs_num(UserForm uf)
		{
			LoginService ls = new LoginService();
			return ls.getGs_num(uf);
		}
		//得到总页数id
		public int getMessagePageCountById(int pageSize,String id,String type)
		{
			MessageService messageService = new MessageService();
			return messageService.getPageCountByid(pageSize, id,type);
		}
		//分页查找id
		public ArrayList getMessagesByPageById(int pageNow,int pageSize,String id,String type){
			MessageService messageService = new MessageService();
			return messageService.getMessagesByPageById(pageNow, pageSize, id,type);
		}
		//获得总记录数
		public int getMessageCount(String id,String type)
		{
			MessageService messageService = new MessageService();
			return messageService.getCount(id,type);
		}
		
		/**
		 * 已做客户
		 * @param id
		 * @return
		 */
		//得到总页数id
		public int getFinishMessagePageCountById(int pageSize,String id,String type)
		{
			MessageService messageService = new MessageService();
			return messageService.getCompletePageCountByid(pageSize, id, type);
		}
		//分页查找id
		public ArrayList getFinishMessagesByPageById(int pageNow,int pageSize,String id,String type){
			MessageService messageService = new MessageService();
			return messageService.getCompletedByPageById(pageNow, pageSize, id, type);
		}
		//获得总记录数
		public int getFinishMessageCount(String id,String type)
		{
			MessageService messageService = new MessageService();
			return messageService.getFinishCount(id, type);
		}
		
		
		//根据id获取留言
		public MessageBean getMessageById(String id)
		{
			MessageService messageService = new MessageService();
			return messageService.getMessageById(id);
		}
		//修改回访
		public boolean modifyHf(String id ,String hf,String lasthf,String flag)
		{
			MessageService messageService = new MessageService();
			return messageService.modifyHf(id, hf, lasthf,flag);
		}
		public UserBean getUserByNum(String num)
		{
			UserService us = new UserService();
			return us.getUserByNum(num);
		}
		//改密码
		public boolean modifyPass(String pws,String id)
		{
			UserService us = new UserService();
			return us.modifyPass(pws, id);
		}
		/**
		 * 任务查询
		 */
		//获取总记录数
		public int getCountByTime(String id,String time,String flag)
		{
			MessageService ms = new MessageService();
			return ms.getCountByTime(id, time,flag);
		}
		//得到总页数
		public int getPageCountByTime(int pageSize,int count)
		{
			MessageService ms = new MessageService();
			return ms.getPageCountByTime(pageSize, count);
		}
		//根据下次回访时间分页查询
		public ArrayList<MessageBean> getMessagesByTime(String time,String id,int pageNow,int pageSize,String flag)
		{
			MessageService ms = new MessageService();
			return ms.getMessagesByTime(time, id, pageNow, pageSize,flag);
		}

		//检查意向存在
//		public boolean checkExist(String messageid,String usernum)
//		{
//			IntentService is = new IntentService();
//			return is.checkExist(messageid, usernum);
//		}
		//添加到意向组
		public boolean InsertIntent(String messageid)
		{
			MessageService ms = new MessageService();
			return ms.intent(messageid);
		}
		public boolean outIntent(String id)
		{
			MessageService ms = new MessageService();
			return ms.outIntent(id);
		}
		public boolean checkByTel(String tel)
		{
			MessageService ms = new MessageService();
			return ms.checkByTel(tel);
		}
		//添加留言
		public boolean addMessage(MessageBean mb)
		{
			MessageService messageService = new MessageService();
			return messageService.addMessage(mb);
		}
		
		//完成
		public boolean finish(String id)
		{
			MessageService messageService = new MessageService();
			return messageService.updateType("3", id);
		}
		//未完成
		public boolean unfinish(String id)
		{
			MessageService messageService = new MessageService();
			return messageService.updateType("1", id);
		}
}
