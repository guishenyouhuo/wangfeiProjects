package com.yashun.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yashun.bean.UserBean;
import com.yashun.manager.AdminManager;

public class FenPeiRenAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fprenId = request.getParameter("id");
		UserBean ub = new UserBean();
		AdminManager adminManager = new AdminManager();
		ub = adminManager.getUserById(fprenId);
		request.setAttribute("fpname", ub.getGs_name());
		return mapping.findForward("ok");
	}

}
