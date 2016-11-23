package com.yashun.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.yashun.bean.UserBean;
import com.yashun.form.AdLoginForm;
import com.yashun.form.UserForm;
import com.yashun.manager.AdminManager;
import com.yashun.manager.UserManager;

public class UserLoginAndOutAction extends DispatchAction {

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8"); 
		UserForm uf = (UserForm) form;
		UserManager um = new UserManager();
		if(um.checkLogin(uf))
		{
			request.getSession().setAttribute("username", uf.getGs_name());
			request.getSession().setAttribute("usernum",um.getGs_num(uf));
			return mapping.findForward("success");
		}
		else
		{
			request.setAttribute("erro", "用户名或密码错误");
			return mapping.findForward("error");
		}
	}
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("usernum");
		return mapping.findForward("out");
	}
	public ActionForward modifyView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userNum = (String)request.getSession().getAttribute("usernum");
		UserManager um = new UserManager();
		UserBean ub = um.getUserByNum(userNum);
		request.setAttribute("ub", ub);
		return mapping.findForward("mdview");
	}
	public ActionForward modifyUserPass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("mid");
		String pws = request.getParameter("pws");
		UserManager um = new UserManager();
		if(um.modifyPass(pws, id))
		{
			return mapping.findForward("mdok");
		}
		return null;
	}

}
