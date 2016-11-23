package com.yashun.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yashun.bean.UserBean;
import com.yashun.manager.AdminManager;

public class ModifyUser extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		AdminManager adminManager = new AdminManager();
		UserBean user = adminManager.getUserByUserId(id);
		request.setAttribute("user", user);
		return mapping.findForward("ok");
	}

}
