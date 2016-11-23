package com.yashun.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.yashun.bean.MessageBean;
import com.yashun.bean.UserBean;
import com.yashun.form.MessageForm;
import com.yashun.manager.AdminManager;
import com.yashun.service.MessageService;
import com.yashun.util.ManProperties;

public class AddMessageByAdmin extends DispatchAction {

	public ActionForward getUsers(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminManager adminManager = new AdminManager();
		ArrayList al = adminManager.getAllUsers();
		request.setAttribute("users", al);
		return arg0.findForward("addview");
	}
	public ActionForward addMessage(ActionMapping arg0, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminManager adminManager = new AdminManager();
		MessageBean mb = new MessageBean();
		MessageForm mf = (MessageForm)form;
		String user_num = "";
		String numFromAd = request.getParameter("select");
		String numFromUser = (String)request.getSession().getAttribute(("usernum"));
		String fromtag = request.getParameter("fromtag");
		if(numFromAd!=null)
		{
			if("autoly".equals(numFromAd))//自动分配
			{
				user_num = adminManager.getAutolyNum();
			}
			else
				user_num = numFromAd;
		}
		else if(numFromUser!=null)
			user_num = numFromUser;
		//System.out.println(mf.getKh_name()+"  "+mf.getKh_tel()+" "+mf.getKh_address()+" "+mf.getKh_ly()+" "+user_num);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String timeNow=df.format(date);
		mb.setKh_name(mf.getKh_name());
		mb.setKh_tel(mf.getKh_tel());
		mb.setKh_address(mf.getKh_address());
		mb.setKh_ly(mf.getKh_ly());
		mb.setFp_user(user_num);
		mb.setIntime(timeNow);
		if(fromtag!=null)
			mb.setTag(fromtag);
		if(adminManager.addMessage(mb))
		{
			if("autoly".equals(numFromAd))//自动分配
			{
				String nowNum = user_num;
				List<UserBean> al = adminManager.getAllOpenUsers();
				for(int i =0 ;i<al.size();i++)
				{
					UserBean ub = al.get(i);
					if(user_num.equals(ub.getGs_num()))
					{
						if(i==al.size()-1)
							nowNum = al.get(0).getGs_num();
						else
							nowNum = al.get(i+1).getGs_num();
						break;
					}
					else
						if(i==al.size()-1)
							nowNum = al.get(0).getGs_num();
				}
				adminManager.updateNowNum(nowNum);
			}
			if(numFromAd!=null)
				return arg0.findForward("ok");
			else if(numFromUser!=null)
				return arg0.findForward("userok");
			else 
				return arg0.findForward("erro");
		}
		else
			return arg0.findForward("erro");
	}
	public ActionForward checkExist(ActionMapping arg0, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tel = request.getParameter("tel");
		AdminManager am = new AdminManager();
		if(am.checkByTel(tel))
		{
			response.getWriter().write("1");
		}
		else 
			response.getWriter().write("0");
		return null;
	}
	public ActionForward showImpotView(ActionMapping arg0, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminManager adminManager = new AdminManager();
		ArrayList al = adminManager.getAllUsers();
		String realPath = this.getServlet().getServletContext().getRealPath("/from.properties");
		List<String> tags = ManProperties.getTags(realPath);
		request.setAttribute("users", al);
		request.setAttribute("tags", tags);
		return arg0.findForward("import");
	}
}
