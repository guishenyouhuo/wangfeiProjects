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
import com.yashun.manager.UserManager;

public class UserLyAction extends DispatchAction {

	public ActionForward showMessages(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pageSize=10;
		int pageNow=1;
		UserManager um = new UserManager();
		String offset = request.getParameter("offset");
		String n_pageNow=request.getParameter("pageNow");
		String userid = (String)request.getSession().getAttribute("usernum");
		String flag = request.getParameter("flag");
		String  u_num=null;
		String type = null;
		if(!"".equals(offset)&&null!=offset)
		{
			pageNow = Integer.parseInt(offset);
		}
		else if(!"".equals(n_pageNow)&&n_pageNow!=null){
			pageNow=Integer.parseInt(n_pageNow);
		}
		if("mine".equals(flag))
		{
			request.getSession().setAttribute("m_type", "1");
		}
		else if("intent".equals(flag))
		{
			request.getSession().setAttribute("m_type", "2");
		}
		else if("unvisited".equals(flag))
		{
			request.getSession().setAttribute("m_type", "0");
		}
		u_num = userid;
		type = (String)request.getSession().getAttribute("m_type");
		int pageCount=um.getMessagePageCountById(pageSize, u_num,type);
		ArrayList al=um.getMessagesByPageById(pageNow, pageSize, u_num,type);
		int count = um.getMessageCount(u_num,type);
		request.setAttribute("messages", al);
		request.setAttribute("pageCount", pageCount);
		request.getSession().setAttribute("pageNow", pageNow+"");
		request.setAttribute("messageCount", count);
		request.setAttribute("userid", userid);
		request.setAttribute("flag", flag);
		return mapping.findForward("success");
	}
	public ActionForward modifyMessageView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String pageNow = request.getParameter("pageNow");
		UserManager um = new UserManager();
		MessageBean mb = um.getMessageById(id);
		if(mb.getLasthf()!=null&&!"".equals(mb.getLasthf()))
			mb.setLasthf(mb.getLasthf().substring(0, 10));
		request.setAttribute("message", mb);
		request.setAttribute("type", type);
		request.setAttribute("pageNow", pageNow);
		return mapping.findForward("view");
	}
	public ActionForward doModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("mid");
		String hf = request.getParameter("textarea");
		String lasthf = request.getParameter("lasthg");
		String type = request.getParameter("type");
		String flag = request.getParameter("flag");
		String pageNow = request.getParameter("pageNow");
		ActionForward forward; 
		if("task".equals(type))
			forward=mapping.findForward("taskmodify");
		else
			forward=mapping.findForward("modifyok");
		ActionForward newForward=new ActionForward(forward);
		String newPath=forward.getPath()+"&pageNow="+pageNow;
		newForward.setPath(newPath);
		UserManager um = new UserManager();
		if(um.modifyHf(id, hf, lasthf,flag))
		{
			return newForward;
		}
		else
			return mapping.findForward("modifyerr");
	}
	public ActionForward intent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String messageid = request.getParameter("messageid");
		String usernum = request.getParameter("usernum");
		UserManager um = new UserManager();
		if(um.InsertIntent(messageid))
		{
			response.getWriter().write("1");
		}
		else
		{
			response.getWriter().write("0");
		}
		return null;
	}
	public ActionForward outIntent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String messageid = request.getParameter("messageid");
		String usernum = request.getParameter("usernum");
		UserManager um = new UserManager();
		if(um.outIntent(messageid))
		{
			response.getWriter().write("1");
		}
		else
		{
			response.getWriter().write("0");
		}
		return null;
	}
}
