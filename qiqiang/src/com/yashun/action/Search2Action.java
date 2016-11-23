package com.yashun.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.yashun.bean.MessageBean;
import com.yashun.manager.AdminManager;

public class Search2Action extends DispatchAction {

	public ActionForward byTel(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tel = request.getParameter("tel");
		String usernum = (String)request.getSession().getAttribute("usernum");
		if(usernum==null)
			return arg0.findForward("index2.jsp");
		AdminManager am = new AdminManager();
		ArrayList<MessageBean> al = am.getMessageBySearch(tel, null, null, null);
		request.setAttribute("messages", al);
		request.setAttribute("nowUser", usernum);
		return arg0.findForward("result");
	}
	public ActionForward byId(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String usernum = (String)request.getSession().getAttribute("usernum");
		if(usernum==null)
			return arg0.findForward("index2.jsp");
		AdminManager am = new AdminManager();
		ArrayList<MessageBean> al = am.getMessageBySearch(null, id, null, null);
		request.setAttribute("messages", al);
		request.setAttribute("nowUser", usernum);
		return arg0.findForward("result");
	}
	public ActionForward byName(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String usernum = (String)request.getSession().getAttribute("usernum");
		if(usernum==null)
			return arg0.findForward("index2.jsp");
		AdminManager am = new AdminManager();
		ArrayList<MessageBean> al = am.getMessageBySearch(null, null, name, null);
		request.setAttribute("messages", al);
		request.setAttribute("nowUser", usernum);
		return arg0.findForward("result");
	}
}
