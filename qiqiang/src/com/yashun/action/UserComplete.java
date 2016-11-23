package com.yashun.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.yashun.manager.UserManager;

public class UserComplete extends DispatchAction {

	public ActionForward LookUserComplete(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageSize=10;
		int pageNow=1;
		UserManager um = new UserManager();
		String offset = request.getParameter("offset");
		String n_pageNow=request.getParameter("pageNow");
		String userid = (String)request.getSession().getAttribute("usernum");
		String flag = request.getParameter("flag");
		String  u_num=null;
		if(!"".equals(offset)&&null!=offset)
		{
			pageNow = Integer.parseInt(offset);
		}
		else if(!"".equals(n_pageNow)&&n_pageNow!=null){
			pageNow=Integer.parseInt(n_pageNow);
		}
		u_num = userid;
		int pageCount=um.getFinishMessagePageCountById(pageSize, u_num,"3");
		ArrayList al=um.getFinishMessagesByPageById(pageNow, pageSize, u_num,"3");
		int count = um.getFinishMessageCount(u_num,"3");
		request.setAttribute("messages", al);
		request.setAttribute("pageCount", pageCount);
		request.getSession().setAttribute("pageNow", pageNow+"");
		request.setAttribute("messageCount", count);
		request.setAttribute("userid", userid);
		request.setAttribute("flag", flag);
		return mapping.findForward("ok");
	}
	public ActionForward doComplete(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String messageid = request.getParameter("id");
		String pageNow = request.getParameter("pageNow");
		UserManager um = new UserManager();
		um.finish(messageid);
		ActionForward forward=mapping.findForward("succ");
		ActionForward newForward=new ActionForward(forward);
		String newPath=forward.getPath() +"&pageNow="+pageNow;
		newForward.setPath(newPath);
		return newForward; 
	}
	public ActionForward unComplete(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String messageid = request.getParameter("id");
		String pageNow = request.getParameter("pageNow");
		UserManager um = new UserManager();
		um.unfinish(messageid);
		ActionForward forward=mapping.findForward("succ");
		ActionForward newForward=new ActionForward(forward);
		String newPath=forward.getPath() +"&pageNow="+pageNow;
		newForward.setPath(newPath);
		return newForward; 
	}

}
