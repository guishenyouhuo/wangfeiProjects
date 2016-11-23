package com.yashun.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.yashun.bean.UserBean;
import com.yashun.form.UserForm;
import com.yashun.manager.UserManager;

public class MarketLogin extends DispatchAction {

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
}
