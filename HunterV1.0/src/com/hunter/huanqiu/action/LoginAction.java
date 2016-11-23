package com.hunter.huanqiu.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hunter.huanqiu.entity.Company;
import com.hunter.huanqiu.entity.Record;
import com.hunter.huanqiu.form.*;
import com.hunter.huanqiu.manager.LoginManager;
import com.hunter.huanqiu.utils.EncryptAndDecrypt;
import com.hunter.huanqiu.utils.GenerateKeyPair;
import com.hunter.huanqiu.utils.UpdateXml;
import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		LoginManager lm=(LoginManager)act.getBean("loginManager");
		UserForm userForm=(UserForm)form;
		String realPath=this.getServlet().getServletContext().getRealPath("/keys.xml");
		String userIp=request.getRemoteHost();
		Cookie[] cookies=request.getCookies();
		Cookie userCookie=null;
		for(int i=0;i<cookies.length;i++){
			if(cookies[i].getName().equals("UserCookie")){
				userCookie=cookies[i];
				break;
			}
		}
		if(userCookie!=null){
			String deStr;
			try {
				String cookieValue=userCookie.getValue();
				String[] keys=UpdateXml.getAttributes(userIp, realPath);
				deStr=EncryptAndDecrypt.decrypt(cookieValue, keys[1]);			
			} catch (Exception e) {
				System.out.println("解密错误");
				String loginInfo=lm.checkInDatabase(userForm);
				if(loginInfo.equals("success")){//解密错误，到数据库判断
					this.saveKeysAndCookie(userIp, userForm, realPath, response,lm);
					request.getSession().setAttribute("user", lm.getUserObj(userForm.getUsername()));
					return mapping.findForward("success");
				}
				else{
					request.setAttribute("error", loginInfo);
					return mapping.findForward("error");
				}
			}
			if(deStr!=null){
				String[] userInf=deStr.split("#");
				if(userForm.getUsername().equals(userInf[0])&&userForm.getPassword().equals(userInf[1])&&(userForm.getUsertype()).equals(userInf[2]))
				{
					request.getSession().setAttribute("user", lm.getUserObj(userForm.getUsername()));
					return mapping.findForward("success");
				}
				else{
					if(!userForm.getUsername().equals(userInf[0])){  ///新用户登录需要到数据库验证
						String loginInfo=lm.checkInDatabase(userForm);
						if(loginInfo.equals("success")){
							this.saveKeysAndCookie(userIp, userForm, realPath, response,lm);
							request.getSession().setAttribute("user", lm.getUserObj(userForm.getUsername()));
							return mapping.findForward("success");
						}
						else {
							request.setAttribute("error", loginInfo);
							return mapping.findForward("error");
						}
					}
					else{
						request.setAttribute("error", "用户名或密码错误~");
						return mapping.findForward("error");
					}
				}
			}else{
				request.setAttribute("error", "用户名或密码错误~");
				return mapping.findForward("error");
			}
		}
		else{//cookie不存在	
			String loginInfo=lm.checkInDatabase(userForm);
			if(loginInfo.equals("success")){
				this.saveKeysAndCookie(userIp, userForm, realPath, response,lm);
				request.getSession().setAttribute("user", lm.getUserObj(userForm.getUsername()));
				return mapping.findForward("success");
			}
			else {
				request.setAttribute("error", loginInfo);
				return mapping.findForward("error");
			}
		}	
	}

	public void saveKeysAndCookie(String userIp,UserForm userForm,String realPath,HttpServletResponse response ,LoginManager lm) throws Exception{
		String[] keys=GenerateKeyPair.getKeyPair();//生成密钥对
		String[] paras={userIp,keys[0],keys[1]};
		UpdateXml.updateNode(realPath, paras);
		String userinfor=userForm.getUsername()+"#"+userForm.getPassword()+"#"+userForm.getUsertype();
		String encryptedInfo=EncryptAndDecrypt.encrypt(userinfor,keys[0]);
		Cookie cookie=new Cookie("UserCookie", encryptedInfo);
		cookie.setMaxAge(12*60*60);
		response.addCookie(cookie);
		String filePath=this.getServlet().getServletContext().getRealPath("/time.txt");//获得文件路径
	    
		//File file=new File(filePath);
		//BufferedReader br = new BufferedReader(new FileReader(file));
		//String data1=br.readLine();
		//String data2=br.readLine();
		
		String data=this.readFile(filePath);
		//System.out.println("time:"+data);
	    DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//当前时间
		Date date = new Date();
		Timestamp currentTime = new Timestamp(date.getTime());//获取当前时间戳
		String current1 = df1.format(currentTime);//获取当前日期
		if(!current1.equals(data))
		{
			//System.out.println("需要更新");
			List<Company> list=lm.getAllCompanyInClue();
			for(int i=0;i<list.size();i++){
				Company company=list.get(i);
				Record lastRecord = lm.getLastRecordByCompanyId(company.getCId());
				String lasttime;
				Timestamp lasttimeInRecord;
				if (lastRecord == null) {
					lasttime = "";
				} else {
					lasttime = df1.format(lastRecord.getRTime());
				}
				if(lasttime==""){
					lasttimeInRecord=company.getCEntrytime();
				}
				else{
					lasttimeInRecord=lastRecord.getRTime();
				}
				Date startTime = df2.parse(lasttimeInRecord.toString());
				Date current = df2.parse(currentTime.toString());
			    long diff =current.getTime() - startTime.getTime();
			    long days = diff / (1000 * 60 * 60 * 24);
			    if(days>=7){
			    	company.setCZt(6);
			    	lm.updateCompany(company);
			    }
			}
			this.writeFile(current1, filePath);
		}
		
		////////////////////////////////////////////////////
		if(!current1.equals(data))
		{
			List<Company> list=lm.getAllCompanyInOther();
			for(int i=0;i<list.size();i++){
				Company company=list.get(i);
				Record lastRecord = lm.getLastRecordByCompanyId(company.getCId());
				String lasttime;
				Timestamp lasttimeInRecord;//获得最后跟进记录时间
				if (lastRecord == null) {
					lasttime = "";
				} 
				else {
					lasttime = df1.format(lastRecord.getRTime());
				}
				if(lasttime==""){
					lasttimeInRecord=company.getCEntrytime();
				}
				else{
					lasttimeInRecord=lastRecord.getRTime();
				}
				Date startTime = df2.parse(lasttimeInRecord.toString());
				Date current = df2.parse(currentTime.toString());
			    long diff =current.getTime() - startTime.getTime();
			    long days = diff / (1000 * 60 * 60 * 24);
			    if(days>=30){
			    	company.setCZt(6);
			    	lm.updateCompany(company);
			    }
			}
			this.writeFile(current1, filePath);
		}
		///////////////////////////////////////////////////
	}
	public String readFile(String filePath){
		BufferedReader br = null;
		String data = "";
		try {
			br = new BufferedReader(new FileReader(filePath));  
		   data = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	public void writeFile(String data,String filePath){
		BufferedWriter writer=null;
		try {
			writer=new BufferedWriter(new FileWriter(filePath));
			writer.write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
