package com.yashun.manager;

import java.util.ArrayList;

import com.yashun.bean.AdminBean;
import com.yashun.bean.AutoBean;
import com.yashun.bean.MessageBean;
import com.yashun.bean.UserBean;
import com.yashun.form.AdLoginForm;
import com.yashun.service.AdminService;
import com.yashun.service.AutolyService;
import com.yashun.service.LoginService;
import com.yashun.service.MessageService;
import com.yashun.service.UserService;

public class AdminManager {
	
	//����û���¼
	public boolean checkLogin(AdLoginForm alf)
	{
		LoginService ls = new LoginService();
		return ls.checkAdLogin(alf);
	}
	//�õ���ҳ��
	public int getMessagePageCount(int pageSize)
	{
		MessageService messageService = new MessageService();
		return messageService.getPageCount(pageSize);
	}
	//��ҳ����
	public ArrayList getMessagesByPage(int pageNow,int pageSize){
		MessageService messageService = new MessageService();
		return messageService.getMessagesByPage(pageNow, pageSize);
	}
	//�õ���ҳ��id
	public int getMessagePageCountById(int pageSize,String id)
	{
		MessageService messageService = new MessageService();
		return messageService.getPageCountByid(pageSize, id,null);
	}
	//��ҳ����id
	public ArrayList getMessagesByPageById(int pageNow,int pageSize,String id){
		MessageService messageService = new MessageService();
		return messageService.getMessagesByPageById(pageNow, pageSize, id,null);
	}
	//����ܼ�¼��
	public int getMessageCount(String id)
	{
		MessageService messageService = new MessageService();
		return messageService.getCount(id,null);
	}
	
	//��ȡ�����ͻ�
	public ArrayList getFinishMessagesByPageById(int pageNow,int pageSize,String id){
		MessageService messageService = new MessageService();
		return messageService.getCompletedByPageById(pageNow, pageSize, id,"3");
	}
	public int getFinishMessagePageCountById(int pageSize,String id)
	{
		MessageService messageService = new MessageService();
		return messageService.getCompletePageCountByid(pageSize, id,"3");
	}
	public int getFinishMessageCount(String id)
	{
		MessageService messageService = new MessageService();
		return messageService.getFinishCount(id,"3");
	}
	
	//ͨ����Ż�÷�����
	public UserBean getUserById(String num)
	{
		MessageService messageService = new MessageService();
		return messageService.getUserById(num);
	}
	//��ȡ���е��û�
	public ArrayList getAllUser()
	{
		MessageService messageService = new MessageService();
		return messageService.getAllUser();
	}
	public boolean moveLy(String id,String user_num,String last_num)
	{
		MessageService messageService = new MessageService();
		return messageService.moveLy(id, user_num,last_num);
	}
	public boolean deleteMessage(String id,String last_num)
	{
		MessageService messageService = new MessageService();
		if("20".equals(last_num))
			return messageService.deleteMessage(id);
		return messageService.moveLy(id, "20",last_num);
	}
	//��������û���Ϣ
	public ArrayList getAllUsers()
	{
		UserService service = new UserService();
		return service.getAllUsers();
	}
	public ArrayList getAllOpenUsers()
	{
		UserService service = new UserService();
		return service.getAllOpenUsers();
	}
	//�����û�
	public boolean openUser(String id)
	{
		UserService userService = new UserService();
		return userService.openUser(id);
	}
	//ͣ���û�
	public boolean closeUser(String id)
	{
		UserService userService = new UserService();
		return userService.closeUser(id);
	}
	//
	public UserBean getUserByUserId(String userid)
	{
		UserService userService = new UserService();
		return userService.getUserById(userid);
	}
	//checkUserNum
	public boolean checkUserNum(String num)
	{
		UserService userService = new UserService();
		UserBean ub = userService.getUserByNum(num);
		if(ub.getGs_num()!=null)
			return true;
		return false;
	}
	//
	public boolean updateUser(String id,String[] params)
	{
		UserService userService = new UserService();
		return userService.updateUserById(id, params);
	}
	//
	public boolean addUser(UserBean ub)
	{
		UserService userService = new UserService();
		return userService.addUser(ub);
	}
	//�������
	public boolean addMessage(MessageBean mb)
	{
		MessageService messageService = new MessageService();
		return messageService.addMessage(mb);
	}
	//��ȡadmin
	public ArrayList<AdminBean> getAdmin()
	{
		AdminService adminService = new AdminService();
		return adminService.getAdmin();
	}
	//�޸�admin
	public boolean modify(AdminBean ab)
	{
		AdminService adminService = new AdminService();
		return adminService.modify(ab);
	}
	//����
	public ArrayList<MessageBean> getMessageBySearch(String tel,String id,String name,String usernum)
	{
		MessageService ms = new MessageService();
		return ms.getMessageBySearch(tel, id, name, usernum);
	}
	public AutoBean getAuto()
	{
		AutolyService as = new AutolyService();
		return as.getAuto();
	}
	public boolean updateAuto(String maxuser,String nownum)
	{
		AutolyService as = new AutolyService();
		return as.updateAuto(maxuser, nownum);
	}
	//ɾ���û�
	public boolean delUser(String id)
	{
		UserService us = new UserService();
		return us.delUser(id);
	}
	public boolean checkByTel(String tel)
	{
		MessageService ms = new MessageService();
		return ms.checkByTel(tel);
	}
	public String getAutolyNum()
	{
		MessageService ms = new MessageService();
		return ms.getAutolyNum();
	}
	public boolean updateNowNum(String nowNum)
	{
		MessageService ms = new MessageService();
		return ms.updateNowNum(nowNum);
	}
}
