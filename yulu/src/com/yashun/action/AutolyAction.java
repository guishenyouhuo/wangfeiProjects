package com.yashun.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.yashun.bean.AutoBean;
import com.yashun.manager.AdminManager;

public class AutolyAction extends DispatchAction {

	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminManager am = new AdminManager();
		AutoBean ab = am.getAuto();
		request.setAttribute("ab", ab);
		return mapping.findForward("view");
	}
	public ActionForward doModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String maxuser = request.getParameter("maxuser");
		String nownum = request.getParameter("nownum");
		AdminManager am = new AdminManager();
		if(am.updateAuto(maxuser, nownum))
			return mapping.findForward("ok");
		else
			return mapping.findForward("err");
	}

}
