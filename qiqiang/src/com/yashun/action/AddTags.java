package com.yashun.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.yashun.util.ManProperties;
import com.yashun.util.ManXmls;

public class AddTags extends DispatchAction {

	public ActionForward toAddTagView(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String realPath = this.getServlet().getServletContext().getRealPath("/template.xml");
		List<String> keys = ManXmls.getkeys(realPath);
		request.setAttribute("templates", keys);
		return arg0.findForward("addview");
	}
	public ActionForward toAddTemplate(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String realPath = this.getServlet().getServletContext().getRealPath("/template.xml");
		String tmpname = request.getParameter("tmpname");
		String hasfirst = request.getParameter("hasfirst");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");
		String message = request.getParameter("message");
		boolean firstline = true;
		if(hasfirst==null)
			firstline = false;
		String[] params = new String[]{tmpname,firstline+"",name,tel,address,message};
		ManXmls.addElement(params, realPath);
		return arg0.findForward("toaddtag");
	}
	public ActionForward toAddTag(ActionMapping arg0, ActionForm arg1,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String realPath = this.getServlet().getServletContext().getRealPath("/from.properties");
		String tag = request.getParameter("tag");
		String template = request.getParameter("template");
		ManProperties.addProperties(tag, template, realPath);
		return arg0.findForward("toimport");
	}
}
