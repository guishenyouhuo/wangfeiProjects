package com.yashun.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.yashun.bean.UserBean;
import com.yashun.manager.AdminManager;

public class DoModifyAction extends DispatchAction {

	public ActionForward checkId(ActionMapping mapping, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse resopnse) throws Exception {
		String num = request.getParameter("num");
		AdminManager adminManager = new AdminManager();
		if(adminManager.checkUserNum(num))
		{
			resopnse.getWriter().write("1");
		}
		else 
			resopnse.getWriter().write("0");
		return null;
	}
	public ActionForward doModify(ActionMapping mapping, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse resopnse) throws Exception {
		request.setCharacterEncoding("utf-8");
		resopnse.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String num = request.getParameter("num");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pws");
		String[] params={name,num,pwd};
		AdminManager adminManager =new AdminManager();
		if(adminManager.updateUser(id, params))
		{
			resopnse.getWriter().write("1");
		}
		else 
			resopnse.getWriter().write("0");
		return null;
	}
	public ActionForward addUser(ActionMapping mapping, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse resopnse) throws Exception {
		String num = request.getParameter("num");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pws");
		UserBean ub = new UserBean();
		ub.setGs_name(name);
		ub.setPws(pwd);
		ub.setGs_num(num);
		ub.setTy_flag(1);
		AdminManager adminManager =new AdminManager();
		if(adminManager.addUser(ub))
		{
			resopnse.getWriter().write("1");
		}
		else 
			resopnse.getWriter().write("0");
		return null;
	}
	public ActionForward delUser(ActionMapping mapping, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse resopnse) throws Exception {
		String id = request.getParameter("id");
		AdminManager adminManager = new AdminManager();
		if(adminManager.delUser(id))
		{
			resopnse.getWriter().write("1");
		}
		else 
			resopnse.getWriter().write("0");
		return null;
	}

}
