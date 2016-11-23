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
	//����û���¼
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
		//�õ���ҳ��id
		public int getMessagePageCountById(int pageSize,String id,String type)
		{
			MessageService messageService = new MessageService();
			return messageService.getPageCountByid(pageSize, id,type);
		}
		//��ҳ����id
		public ArrayList getMessagesByPageById(int pageNow,int pageSize,String id,String type){
			MessageService messageService = new MessageService();
			return messageService.getMessagesByPageById(pageNow, pageSize, id,type);
		}
		//����ܼ�¼��
		public int getMessageCount(String id,String type)
		{
			MessageService messageService = new MessageService();
			return messageService.getCount(id,type);
		}
		
		/**
		 * �����ͻ�
		 * @param id
		 * @return
		 */
		//�õ���ҳ��id
		public int getFinishMessagePageCountById(int pageSize,String id,String type)
		{
			MessageService messageService = new MessageService();
			return messageService.getCompletePageCountByid(pageSize, id, type);
		}
		//��ҳ����id
		public ArrayList getFinishMessagesByPageById(int pageNow,int pageSize,String id,String type){
			MessageService messageService = new MessageService();
			return messageService.getCompletedByPageById(pageNow, pageSize, id, type);
		}
		//����ܼ�¼��
		public int getFinishMessageCount(String id,String type)
		{
			MessageService messageService = new MessageService();
			return messageService.getFinishCount(id, type);
		}
		
		
		//����id��ȡ����
		public MessageBean getMessageById(String id)
		{
			MessageService messageService = new MessageService();
			return messageService.getMessageById(id);
		}
		//�޸Ļط�
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
		//������
		public boolean modifyPass(String pws,String id)
		{
			UserService us = new UserService();
			return us.modifyPass(pws, id);
		}
		/**
		 * �����ѯ
		 */
		//��ȡ�ܼ�¼��
		public int getCountByTime(String id,String time,String flag)
		{
			MessageService ms = new MessageService();
			return ms.getCountByTime(id, time,flag);
		}
		//�õ���ҳ��
		public int getPageCountByTime(int pageSize,int count)
		{
			MessageService ms = new MessageService();
			return ms.getPageCountByTime(pageSize, count);
		}
		//�����´λط�ʱ���ҳ��ѯ
		public ArrayList<MessageBean> getMessagesByTime(String time,String id,int pageNow,int pageSize,String flag)
		{
			MessageService ms = new MessageService();
			return ms.getMessagesByTime(time, id, pageNow, pageSize,flag);
		}

		//����������
//		public boolean checkExist(String messageid,String usernum)
//		{
//			IntentService is = new IntentService();
//			return is.checkExist(messageid, usernum);
//		}
		//��ӵ�������
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
		//�������
		public boolean addMessage(MessageBean mb)
		{
			MessageService messageService = new MessageService();
			return messageService.addMessage(mb);
		}
		
		//���
		public boolean finish(String id)
		{
			MessageService messageService = new MessageService();
			return messageService.updateType("3", id);
		}
		//δ���
		public boolean unfinish(String id)
		{
			MessageService messageService = new MessageService();
			return messageService.updateType("1", id);
		}
}
