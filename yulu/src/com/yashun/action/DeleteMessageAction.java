package com.yashun.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yashun.manager.AdminManager;

public class DeleteMessageAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdminManager adminManager = new AdminManager();
		String id = request.getParameter("id");
		String last_num = request.getParameter("last_num");
		if(adminManager.deleteMessage(id,last_num))
		{
			response.getWriter().write("1");
		}
		else
		{
			response.getWriter().write("0");
		}
		return null;
	}

}
