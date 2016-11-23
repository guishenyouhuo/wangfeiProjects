package com.yashun.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yashun.manager.AdminManager;

public class LookFinish extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdminManager adminManager = new AdminManager();
		int pageSize=10;
		int pageNow=1;
		String offset = request.getParameter("offset");
		String flag = request.getParameter("flag");
		String userid=null;
		if("all".equals(flag))
			request.getSession().removeAttribute("userid");
		else if("user".equals(flag))
		{
			userid = request.getParameter("id");
			request.getSession().setAttribute("userid", userid);
		}
		userid = (String)request.getSession().getAttribute("userid");
		int pageCount=adminManager.getFinishMessagePageCountById(pageSize, userid);
		String n_pageNow=request.getParameter("pageNow");
		if(!"".equals(offset)&&null!=offset)
		{
			pageNow = Integer.parseInt(offset);
		}
		else if(n_pageNow!=null){
			pageNow=Integer.parseInt(n_pageNow);
		}
		ArrayList al=adminManager.getFinishMessagesByPageById(pageNow, pageSize, userid);
		int count = adminManager.getFinishMessageCount(userid);
		request.setAttribute("messages", al);
		request.setAttribute("pageCount", pageCount);
		request.getSession().setAttribute("pageNow", pageNow+"");
		request.setAttribute("messageCount", count);
		return mapping.findForward("ok");
	}

}
