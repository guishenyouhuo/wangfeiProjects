package com.yashun.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yashun.manager.UserManager;

public class TaskAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String flag = request.getParameter("flag");//tomorrow  today
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String timeNow = df.format(date);
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(date);
	    calendar.add(calendar.DATE,1);//明天
	    date=calendar.getTime();
	    String timeTomorrow = df.format(date);
	    calendar.add(calendar.DATE,1);//后天
	    date=calendar.getTime();
	    String afterTomorrow = df.format(date);
		String time = "";
		if("today".equals(flag)||"history".equals(flag))
		{
			request.getSession().setAttribute("time", timeNow);
		}
		else if("tomorrow".equals(flag))
		{
			request.getSession().setAttribute("time", timeTomorrow);
		}
		else if("aftertomorrow".equals(flag))
		{
			request.getSession().setAttribute("time", afterTomorrow);
		}
		if(flag==null)
			flag = (String) request.getSession().getAttribute("taskflag");
		request.getSession().setAttribute("taskflag", flag);
		time = (String)request.getSession().getAttribute("time");
		String user_num = (String)request.getSession().getAttribute("usernum");
		if(user_num==null)
			return mapping.findForward("timeout");
		UserManager um = new UserManager();
		int pageSize=10;
		int pageNow=1;
		String offset = request.getParameter("offset");
		String n_pageNow=request.getParameter("pageNow");
		if(!"".equals(offset)&&null!=offset)
		{
			pageNow = Integer.parseInt(offset);
		}
		else if(n_pageNow!=null){
			pageNow=Integer.parseInt(n_pageNow);
		}
		ArrayList al = um.getMessagesByTime(time, user_num, pageNow, pageSize,flag);
		int count = um.getCountByTime(user_num, time,flag);
		int pageCount = um.getPageCountByTime(pageSize, count);
		request.setAttribute("messages", al);
		request.setAttribute("pageCount", pageCount);
		request.getSession().setAttribute("pageNow", pageNow+"");
		request.setAttribute("messageCount", count);
		if("history".equals(flag))
			request.setAttribute("tasktime", time+"之前");
		else
			request.setAttribute("tasktime", time);
		request.setAttribute("flag", flag);
		return mapping.findForward("ok");
	}

}
