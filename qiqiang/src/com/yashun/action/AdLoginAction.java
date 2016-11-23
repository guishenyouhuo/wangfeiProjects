package com.yashun.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yashun.form.AdLoginForm;
import com.yashun.manager.AdminManager;

public class AdLoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8"); 
		AdLoginForm alf = (AdLoginForm) form;
		AdminManager am = new AdminManager();
		if(am.checkLogin(alf))
		{
			request.getSession().setAttribute("username", alf.getAdminname());
			return mapping.findForward("success");
		}
		else
		{
			request.setAttribute("erro", "用户名或密码错误");
			return mapping.findForward("error");
		}
		
	}

}
