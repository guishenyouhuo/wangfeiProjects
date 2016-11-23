package com.hunter.huanqiu.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hunter.huanqiu.utils.JacobUtil;

public class WordToHtmlServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String type=request.getParameter("type");
		if(type.equals("contract")){
			String conname=request.getParameter("conname");
			System.out.println(conname);
			String name=conname.substring(0, conname.lastIndexOf("."));
			String realPath=this.getServletContext().getRealPath("/contracthtmls");
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
		            response.getWriter().write(0);  
		        }    
		    }   
		    else{
		    	response.getWriter().write(name+".html");
		    }	
		}
		if(type.equals("resume")){
			
			String EResume_name=request.getParameter("EResume_name");
			System.out.println("EResume_name:"+EResume_name);
			String name=EResume_name.substring(0, EResume_name.lastIndexOf("."));
			System.out.println("name:"+name);
			String realPath=this.getServletContext().getRealPath("/resumehtmls");
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
		}
	}
}
