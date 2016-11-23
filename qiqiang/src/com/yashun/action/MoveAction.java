package com.yashun.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yashun.manager.AdminManager;

public class MoveAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String select = request.getParameter("select");
		String id = request.getParameter("id");
		String last_num = request.getParameter("last_num");
		AdminManager adminManager = new AdminManager();
		if(!select.equals(last_num))
		{
			if(adminManager.moveLy(id, select,last_num))
			{
				response.getWriter().write("1");
			}
			else
			{
				response.getWriter().write("0");
			}
		}
		else 
			response.getWriter().write("1");
		return null;
	}

}
