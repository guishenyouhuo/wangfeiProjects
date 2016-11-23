package com.hunter.huanqiu.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hunter.huanqiu.utils.JacobUtil;

public class WordToHtmlAction extends DispatchAction {

	public ActionForward contract(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("#####");
		String conname=request.getParameter("conname");
		System.out.println(conname+"struts");
		String name=conname.substring(0, conname.lastIndexOf("."));
		String realPath=this.getServlet().getServletContext().getRealPath("/contracthtmls");
		String htmlfile=realPath+"\\"+name+".html";
		File file=new File(htmlfile);
		if(!file.exists())    
	    {    
	        try {    
	        	String docfile = "D:\\contracts\\"+conname;
	    		JacobUtil.wordToHtml(docfile, htmlfile);    
	    		response.getWriter().write(name+".html");
	        } catch (Exception e) {    
	            // TODO Auto-generated catch block    
	            e.printStackTrace(); 
	            
	        }    
	    }   
	    else{
	    	response.getWriter().write(name+".html");
	    }	
		return null;
	}
	public ActionForward resume(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String EResume_name=request.getParameter("EResume_name");
		System.out.println("EResume_name:"+EResume_name);
		String name=EResume_name.substring(0, EResume_name.lastIndexOf("."));
		System.out.println("name:"+name);
		String realPath=this.getServlet().getServletContext().getRealPath("/resumehtmls");
		String htmlfile=realPath+"\\"+name+".html";
		System.out.println("htmlfile:"+htmlfile);
		File file=new File(htmlfile);
		if(!file.exists())    
	    {    
	        try {    
	        	String docfile = "D:\\resumes\\"+EResume_name;
	        	System.out.println("docfile:"+docfile);
	    		JacobUtil.wordToHtml(docfile, htmlfile);    
	    		response.getWriter().write(name+".html");
	        } catch (Exception e) {    
	            // TODO Auto-generated catch block    
	            e.printStackTrace(); 
	            response.getWriter().write(0);  
	        }    
	    }   
	    else{
	    	response.getWriter().write(name+".html");
	    }	
		return null;
	}
}
