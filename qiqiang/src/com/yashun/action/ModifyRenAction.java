package com.yashun.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yashun.manager.AdminManager;

public class ModifyRenAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdminManager adminManager = new AdminManager();
		String num = request.getParameter("num");
		String id = request.getParameter("id");
		ArrayList al = adminManager.getAllUser();
		request.setAttribute("users", al);
		request.setAttribute("num", num);
		request.setAttribute("id", id);
		return mapping.findForward("ok");
	}

}
