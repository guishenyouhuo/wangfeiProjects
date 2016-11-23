package com.hunter.huanqiu.utils;

import java.io.File;
import java.io.IOException;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class JacobUtil {
	
	// 8 ����word�����html
	public static final int WORD_HTML = 8; 

//	public static void main(String[] args) {
//	    File file=new File("C:\\Users\\Administrator\\Desktop\\temp.html");    
//	    if(!file.exists())    
//	    {    
//	    	System.out.println("������");
//	        try {    
//	        	String docfile = "C:\\Users\\Administrator\\Desktop\\֪ʶ��ʾ.docx";
//	    		String htmlfile = "C:\\Users\\Administrator\\Desktop\\temp.html";
//	    		JacobUtil.wordToHtml(docfile, htmlfile);    
//	        } catch (Exception e) {    
//	            // TODO Auto-generated catch block    
//	            e.printStackTrace();    
//	        }    
//	    }   
//	    else{
//	    	System.out.println("����");
//	    }
//		
//	}
	
    /**  
     * WORDתHTML  
     * @param docfile WORD�ļ�ȫ·��  
     * @param htmlfile ת����HTML���·��  
     */  
    public static void wordToHtml(String docfile, String htmlfile)   
    {   
    	// ����wordӦ�ó���(Microsoft Office Word 2003)
        ActiveXComponent app = new ActiveXComponent("Word.Application");
        System.out.println("*****����ת��...*****");
        try  
        {	
        	// ����wordӦ�ó��򲻿ɼ�  
            app.setProperty("Visible", new Variant(false));  
            // documents��ʾword����������ĵ����ڣ���word�Ƕ��ĵ�Ӧ�ó���
            Dispatch docs = app.getProperty("Documents").toDispatch();  
            // ��Ҫת����word�ļ�
            Dispatch doc = Dispatch.invoke(   
                    docs,   
                    "Open",   
                    Dispatch.Method,   
                    new Object[] { docfile, new Variant(false), 
                    		new Variant(true) }, new int[1]).toDispatch();   
            // ��Ϊhtml��ʽ���浽��ʱ�ļ�
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {   
                    htmlfile, new Variant(WORD_HTML) }, new int[1]);   
            // �ر�word�ļ�
            Dispatch.call(doc, "Close", new Variant(false));   
        }   
        catch (Exception e)   
        {   
            e.printStackTrace();              		
        }   
        finally  
        {   
        	//�ر�wordӦ�ó���
            app.invoke("Quit", new Variant[] {});   
        } 
        System.out.println("*****ת�����********");
    }
}