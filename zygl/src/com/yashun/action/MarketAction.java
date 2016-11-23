package com.yashun.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.yashun.bean.MarketMsgBean;
import com.yashun.bean.MessageBean;
import com.yashun.form.MessageForm;
import com.yashun.manager.AdminManager;
import com.yashun.manager.MarketManager;
import com.yashun.manager.UserManager;

public class MarketAction extends DispatchAction {

	public ActionForward showMarLy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int pageSize=10;
		int pageNow=1;
		MarketManager mm = new MarketManager();
		String n_pageNow=request.getParameter("pageNow");
		String userid =(String) request.getSession().getAttribute("usernum");
		String  u_num=null;

		if(!"".equals(n_pageNow)&&n_pageNow!=null){
			pageNow=Integer.parseInt(n_pageNow);
		}
		u_num = userid;
		ArrayList al=mm.getMessagesByPage(pageNow, pageSize,u_num);
		int count = mm.getCount(u_num);
		int pageCount=mm.getPageCount(u_num, pageSize, count);
		request.setAttribute("messages", al);
		request.setAttribute("pageCount", pageCount);
		request.getSession().setAttribute("pageNow", pageNow+"");
		request.setAttribute("messageCount", count);
		return mapping.findForward("success");
	}
	
	public ActionForward checkExist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tel = request.getParameter("tel");
		MarketManager am = new MarketManager();
		if(am.checkByTel(tel))
		{
			response.getWriter().write("1");
		}
		else 
			response.getWriter().write("0");
		return null;
	}
	
	public ActionForward addMessage(ActionMapping arg0, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		MarketManager am = new MarketManager();
		MarketMsgBean mb = new MarketMsgBean();
		MessageForm mf = (MessageForm)form;
		String numFromUser = (String)request.getSession().getAttribute(("usernum"));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String timeNow=df.format(date);
		mb.setKh_name(mf.getKh_name());
		mb.setKh_tel(mf.getKh_tel());
		mb.setKh_address(mf.getKh_address());
		mb.setKh_ly(mf.getKh_ly());
		mb.setFp_user(numFromUser);
		mb.setIntime(timeNow);
		
		if(!am.checkByTel(mf.getKh_tel()))
			am.addMessage(mb);
		return arg0.findForward("addok");

	}
	
	public ActionForward modifyMessageView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pageNow = request.getParameter("pageNow");
		MarketManager am = new MarketManager();
		MarketMsgBean mb = am.getMessageById(id);
		if(mb.getLasthf()!=null&&!"".equals(mb.getLasthf()))
			mb.setLasthf(mb.getLasthf().substring(0, 10));
		request.setAttribute("message", mb);
		request.setAttribute("pageNow", pageNow);
		return mapping.findForward("view");
	}
	
	public ActionForward doModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("mid");
		String hf = request.getParameter("textarea");
		String lasthf = request.getParameter("lasthg");
		String pageNow = request.getParameter("pageNow");
		ActionForward forward=mapping.findForward("modifyok");
		ActionForward newForward=new ActionForward(forward);
		String newPath=forward.getPath()+"&pageNow="+pageNow;
		String username = (String)request.getSession().getAttribute(("username"));
		newForward.setPath(newPath);
		MarketManager am = new MarketManager();
		MarketMsgBean mb = am.getMessageById(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String timeNow=df.format(date);
		String hist=mb.getFirstinfo();
		String temp=timeNow+"----"+username;
		if("".equals(hist)||hist==null)
			hist=temp;
		else
			hist+="</br>"+temp;
		am.modifyHf(id, hf, lasthf,hist);
		return newForward;
	}
	
}
