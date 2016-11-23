package com.yashun.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.yashun.bean.AdminBean;
import com.yashun.form.AdminForm;
import com.yashun.manager.AdminManager;

public class AdminModify extends DispatchAction {

	
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminManager adminManager = new AdminManager();
		ArrayList<AdminBean> al = adminManager.getAdmin();
		request.setAttribute("admins", al);
		return mapping.findForward("view");
	}
	public ActionForward action(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminManager adminManager = new AdminManager();
		AdminForm af = (AdminForm)form;
		String id = request.getParameter("id");
		AdminBean ab = new AdminBean();
		ab.setId(id);
		ab.setName(af.getAdminname());
		ab.setPass(af.getAdminpass());
		ab.setGs(af.getVar_gs());
		adminManager.modify(ab);
		return mapping.findForward("ok");
	}
	
}
