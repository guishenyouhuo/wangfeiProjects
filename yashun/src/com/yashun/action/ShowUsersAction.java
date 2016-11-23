package com.yashun.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yashun.manager.AdminManager;

public class ShowUsersAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdminManager adminManager = new AdminManager();
		ArrayList al = adminManager.getAllUsers();
		request.setAttribute("users", al);
		return mapping.findForward("ok");
	}

}
