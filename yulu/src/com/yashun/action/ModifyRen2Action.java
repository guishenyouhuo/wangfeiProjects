package com.yashun.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yashun.manager.AdminManager;

public class ModifyRen2Action extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdminManager adminManager = new AdminManager();
		String num = request.getParameter("num");
		String id = request.getParameter("id");
		String flag = request.getParameter("flag");
		String type = request.getParameter("type");
		ArrayList al = adminManager.getAllUser();
		request.setAttribute("users", al);
		request.setAttribute("num", num);
		request.setAttribute("id", id);
		request.setAttribute("flag", flag);
		request.setAttribute("type", type);
		return mapping.findForward("ok");
	}
}
