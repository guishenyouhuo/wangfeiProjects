package com.yashun.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.yashun.manager.AdminManager;

public class OpenAndCloseAction extends DispatchAction {

	public ActionForward open(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminManager adminManager = new AdminManager();
		String id = request.getParameter("id");
		if(adminManager.openUser(id))
		{
			response.getWriter().write("1");
		}
		else
			response.getWriter().write("0");
		return null;
	}
	public ActionForward close(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		AdminManager adminManager = new AdminManager();
		String id = request.getParameter("id");
		if(adminManager.closeUser(id))
		{
			response.getWriter().write("1");
		}
		else
			response.getWriter().write("0");
		return null;
	}

}
