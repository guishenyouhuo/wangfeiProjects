package com.hunter.huanqiu.action;

import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hunter.huanqiu.entity.*;
import com.hunter.huanqiu.form.*;
import com.hunter.huanqiu.manager.*;
import com.hunter.huanqiu.utils.EncryptAndDecrypt;
import com.hunter.huanqiu.utils.UpdateXml;

public class CompanyAction extends DispatchAction {
	ApplicationContext act;
	
	public ActionForward addCompany(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		User user=(User)request.getSession().getAttribute("user");
		if(user==null){
			return actionMapping.findForward("success");
		}
		Company company = new Company();
		company.setCId(UUID.randomUUID().toString());
		// 获取当前时间
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		company.setCEntrytime(ts);
		this.companyCl(response, request, actionForm,company);
		company.setUser(user);
		company.setCZt(1);
		cm.addCompany(company);
		return null;
	}
	public ActionForward updateCompany(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		User user=(User)request.getSession().getAttribute("user");
		if(user==null){
			return actionMapping.findForward("success");
		}
		String cid=request.getParameter("cid");
		Company company = cm.getCompanyById(cid);
		this.companyCl(response, request, actionForm, company);
		cm.updateCompany(company);
		return actionMapping.findForward("success");
	}
	public ActionForward deleteCompany(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		String cid=request.getParameter("cid");
		String serverPath=this.getServlet().getServletContext().getRealPath("/contracthtmls");
		String SerBillPath=this.getServlet().getServletContext().getRealPath("/bills");
		String[] cids=cid.split(",");
		for(int i=0;i<cids.length;i++){
			Company company=cm.getCompanyById(cids[i]);
			cm.deleteCompany(company);
			Contract contract=cm.getContractByCid(cids[i]);
			if(contract!=null){
				String contractNewname=contract.getConNewname();
				File contractDoc=new File("D://contracts//"+contractNewname);
				File contractHtml=new File(serverPath+"//"+contractNewname.substring(0, contractNewname.indexOf("."))+".html");
				File contracts=new File(serverPath+"//"+contractNewname.substring(0, contractNewname.indexOf("."))+".files");
				if(contracts.exists()){
					if(contracts.isDirectory()){
						 File files[] = contracts.listFiles();               //声明目录下所有的文件 files[];
					     for(int j=0;j<files.length;j++){            //遍历目录下所有的文件
					      files[j].delete();          //把每个文件 用这个方法进行迭代
						}
					     contracts.delete();
					}
				}
				if(contractHtml.exists()){
					contractHtml.delete();
				}
				if(contractDoc.exists()){
					contractDoc.delete();
				}
				List<Bill> bills=cm.getBillsByConid(contract.getConId());
				for(int j=0;j<bills.size();j++){
					Bill bill=bills.get(j);
					File diskBill=new File("D://bills//"+bill.getBillNewname());
					File serBill=new File(SerBillPath+"//"+bill.getBillNewname());
					if(serBill.exists()){
						serBill.delete();
					}					
					if(diskBill.exists()){
						diskBill.delete();
					}
				}
			}
		}
		return null;
	}
	//获取数据库数据返回给页面
	public ActionForward getCompany(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		String cid=request.getParameter("cid");
		User user=(User)request.getSession().getAttribute("user");
		if(user==null){
			return actionMapping.findForward("success");
		}
		Company company=cm.getCompanyById(cid);
		String data=company.getCName()+","+company.getCAddress()+","+company.getCLinkman()+","+company.getCSex()+
							 ","+company.getCTitle()+","+company.getCEmail()+","+company.getCPhone()+","+company.getCId()+","+company.getCTelephone()+","+
				company.getCSecondlinkman()+","+company.getCSecondphone()+","+company.getCSecondphone()+","+company.getCNeed();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(data);
		return null;
	}

	public ActionForward showCompanyList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int total=0;
		List<Object> rows;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		User user=(User)request.getSession().getAttribute("user");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));// 接受参数page和rows
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		String key = request.getParameter("key");
		String address_search=request.getParameter("address_search");
		String type=request.getParameter("type");
		String zt=request.getParameter("zt");
		rows = new ArrayList<Object>();
		List<Company> list =null;
		if(type==null){
			if(user.getUsertype().equals("manager")){
				list = cm.getCompanys(page, row, order, sort, key, address_search, null,Integer.parseInt(zt));
				total = cm.getCompanyCount(key,address_search,null,Integer.parseInt(zt));
			}
			else{
				 list = cm.getCompanys(page, row, order, sort, key,address_search,user.getUsername(),Integer.parseInt(zt));
				total = cm.getCompanyCount(key,address_search,user.getUsername(),Integer.parseInt(zt));
			}
		}
		else if(type.equals("commen")){
			 list = cm.getCompanys(page, row, order, sort, key,address_search, null,Integer.parseInt(zt));
			 total = cm.getCompanyCount(key,address_search,null,Integer.parseInt(zt));
		}

		for (int i = 0; i < list.size(); i++) {
			Company company = list.get(i); 
			Map<String, Object> map = new HashMap<String, Object>();
			Record lastRecord = cm.getLastRecordByCompanyId(company.getCId());
			String lasttime;
			if (lastRecord == null) {
				lasttime = "";
			} else {
				lasttime = df.format(lastRecord.getRTime());
			}
			this.setMap(map, company, df, lasttime);
			rows.add(map); // 循环保存map到list对象this.rows中
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonObj.toString());
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}
	
	public ActionForward checkCompanyName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		String companyname=request.getParameter("company");
		if(cm.checkCompanyInDb(companyname)){
			response.getWriter().write("exist");
		}
		else{
			response.getWriter().write("notexist");
		}
		return null;
	}
	
	//签单未到账数据获取
	public ActionForward showWriteNoBillList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int total;
		List<Object> rows;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		User user=(User)request.getSession().getAttribute("user");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));// 接受参数page和rows
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		String key = request.getParameter("key");
		String address_search=request.getParameter("address_search");
		String zt=request.getParameter("zt");
		rows = new ArrayList<Object>();
		List<Company> list=new ArrayList<Company>() ;
		if(user.getUsertype().equals("manager")){
			list= cm.getCompanys(page, row, order, sort, key,address_search, null,Integer.parseInt(zt));
			total = cm.getCompanyCount(key,address_search,null,Integer.parseInt(zt));
		}
		else{
			list= cm.getCompanys(page, row, order, sort, key, address_search, user.getUsername(),Integer.parseInt(zt));
			total = cm.getCompanyCount(key,address_search,user.getUsername(),Integer.parseInt(zt));
		}

		for (int i = 0; i < list.size(); i++) {
			Company company = list.get(i); 
			Map<String, Object> map = new HashMap<String, Object>();
			Contract contract=cm.getContractByCid(company.getCId());
			String contracttime="";
			String billtime="";
			float billMoney=0;
			if(contract!=null){
				contracttime=df.format(contract.getConTime());
			}
			Bill bill=cm.getBillByConid(contract.getConId());
			if(bill!=null){		
				billtime=df.format(bill.getBillTime());
			}
			billMoney=cm.getpaidMoneyByConid(contract.getConId());
			float shouldPay=contract.getConMoney()-billMoney;
			map.put("cid", company.getCId()); 
			map.put("conid", contract.getConId());
			map.put("connewname", contract.getConNewname()); 
			map.put("conname", contract.getConName()); 
			map.put("CName", company.getCName()); // 以键值对的形式保存到map中
			map.put("CAddress", company.getCAddress());
			map.put("contractmoney", contract.getConMoney()); // 以键值对的形式保存到map中
			map.put("paidmoney", billMoney);
			map.put("concuptime", contracttime);
			map.put("entryuser", company.getUser().getUsername());
			map.put("lastbilltime", billtime);	
			map.put("shouldPay", shouldPay); 
			rows.add(map); // 循环保存map到list对象this.rows中
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("rows", rows);
		jsonObj.put("total", total);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonObj.toString());
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}
	
	public void companyCl(HttpServletResponse response,HttpServletRequest request,ActionForm actionForm,Company company) throws Exception{	
		CompanyForm companyForm = (CompanyForm) actionForm;

		// 封装company类
		company.setCLinkman(companyForm.getCompany_linkman());
		company.setCAddress(companyForm.getCompany_address());
		company.setCEmail(companyForm.getCompany_email());
		
		company.setCName(companyForm.getCompany_name());
		company.setCPhone(companyForm.getCompany_phone());
		company.setCSex(companyForm.getCompany_sex() + "");
		company.setCTitle(companyForm.getCompany_title());
		company.setCTelephone(companyForm.getCompany_telephone());
		company.setCSecondlinkman(companyForm.getCompany_secondlinkman());
		company.setCSecondphone(companyForm.getCompany_secondphone());
		company.setCNeed(companyForm.getCompany_need());
		
	}
	//从cookie中获得用户名，然后再得到用户，此方法可以不用，用户名可以直接从前台传入
	public User getUser(HttpServletResponse response,HttpServletRequest request) throws Exception{
		
		//读取cookie获得username
	    Cookie[] cookies=request.getCookies();
		Cookie userCookie=null;
		String deStr;
		String realPath=this.getServlet().getServletContext().getRealPath("/keys.xml");
		String userIp=request.getRemoteHost();
		String username=null;
		User user=null;
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().equals("UserCookie")){
					userCookie=cookies[i];
					break;
				}
			}
		}
		if(userCookie!=null){
			String cookieValue=userCookie.getValue();
			String[] keys=UpdateXml.getAttributes(userIp, realPath);
			deStr=EncryptAndDecrypt.decrypt(cookieValue, keys[1]);
			username=deStr.split("#")[0];
			user=(User)request.getSession().getAttribute(username);
		}
		return user;
	}
	public void setMap(Map<String, Object> map,Company company,SimpleDateFormat df,String lasttime){
		map.put("cid", company.getCId()); 
		map.put("CName", company.getCName()); // 以键值对的形式保存到map中
		map.put("CAddress", company.getCAddress());
		map.put("CTelephone",company.getCTelephone());
		map.put("CLinkman", company.getCLinkman());
		map.put("CPhone", company.getCPhone());
		map.put("CEntrytime", df.format(company.getCEntrytime()));
		map.put("entryuser", company.getUser().getUsername());
		map.put("lasttime", lasttime);
	}
}
