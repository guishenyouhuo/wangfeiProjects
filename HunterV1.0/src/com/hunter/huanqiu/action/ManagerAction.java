package com.hunter.huanqiu.action;

import java.sql.Timestamp;
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

import com.hunter.huanqiu.entity.Bill;
import com.hunter.huanqiu.entity.Company;
import com.hunter.huanqiu.entity.Contract;
import com.hunter.huanqiu.entity.Record;
import com.hunter.huanqiu.entity.User;
import com.hunter.huanqiu.form.UserForm;
import com.hunter.huanqiu.manager.CompanyMananger;
import com.hunter.huanqiu.manager.LoginManager;
import com.hunter.huanqiu.manager.UserManager;
import com.hunter.huanqiu.utils.EncryptAndDecrypt;
import com.hunter.huanqiu.utils.UpdateXml;

public class ManagerAction extends DispatchAction {

	
	public ActionForward showUserList(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		UserManager um=(UserManager)act.getBean("userManager");
		List<Object> rows;
		int total;
		  int page =Integer.parseInt(request.getParameter("page"));
		  int row = Integer.parseInt(request.getParameter("rows"));//接受参数page和rows
		  String order=request.getParameter("order");
		  String sort=request.getParameter("sort");
		  String key=request.getParameter("userkey");
		  rows = new ArrayList<Object>();
		  List<User> list = um.getUsers(page,row,order,sort,key);
		  total=um.getUserCount(key);
		  for(int i=0;i<list.size();i++){
			   User user  = list.get(i);    //最原始循环方法到到student对象
			   Map<String,Object> map = new HashMap<String,Object>();
			   map.put("username", user.getUsername());
			   map.put("password",user.getPassword());
			   map.put("usertype",user.getUsertype());
			   map.put("zt", user.getZt());        //以键值对的形式保存到map中

			   rows.add(map);       //循环保存map到list对象this.rows中
			  }
		  net.sf.json.JSONObject jsonObj = new net.sf.json.JSONObject();
		  jsonObj.put("rows", rows);
		  jsonObj.put("total", total);
		  response.setCharacterEncoding("utf-8");
		  response.getWriter().write(jsonObj.toString());
        response.getWriter().flush();
        response.getWriter().close();
		return null;
	}

	public ActionForward checkUser(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		UserManager um=(UserManager)act.getBean("userManager");
		String username=request.getParameter("username");
		if(um.checkUserInDb(username)){
			response.getWriter().write("exist");
		}
		else{
			response.getWriter().write("notexist");
		}
		return null;
	}
	public ActionForward addUser(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		UserManager um=(UserManager)act.getBean("userManager");
		UserForm userForm=(UserForm)(actionForm);
		User user =new User();
		user.setUsername(userForm.getUsername());
		user.setPassword(userForm.getPassword());
		user.setUsertype(userForm.getUsertype());
		user.setZt(1);
		um.addUser(user);
		return null;
	}
	public ActionForward updateUser(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		UserManager um=(UserManager)act.getBean("userManager");
		String oldname=request.getParameter("oldname");
		UserForm userForm=(UserForm)(actionForm);
		User user =um.getUserByUsername(oldname);
		user.setUsername(userForm.getUsername());
		user.setPassword(userForm.getPassword());
		user.setUsertype(userForm.getUsertype());
		user.setZt(1);
		um.updateUser(user);
		return null;
	}
	public ActionForward CheckPassword(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		UserManager um=(UserManager)act.getBean("userManager");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=um.getUserByUsername(username);
		if(user==null){
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("0");
		}
		else if(user.getPassword().equals(password)){
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("1");
		}
		else{
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("0");
		}
		return null;
	}
	public ActionForward updatePassword(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		UserManager um=(UserManager)act.getBean("userManager");
		String username=request.getParameter("username");
		String newpwd=request.getParameter("newpwd");
		User user =um.getUserByUsername(username);
		user.setPassword(newpwd);
		um.updateUser(user);
		request.getSession().removeAttribute(username);
		Cookie[] cookies=request.getCookies();
		Cookie userCookie=null;
		for(int i=0;i<cookies.length;i++){
			if(cookies[i].getName().equals("UserCookie")){
				userCookie=cookies[i];
				break;
			}
		}
		if(userCookie!=null){
			userCookie.setMaxAge(0);
			response.addCookie(userCookie);
		}
		return mapping.findForward("success");
	}
	public ActionForward deleteUser(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		UserManager um=(UserManager)act.getBean("userManager");
		String username=request.getParameter("username");
		String[] usernames=username.split(",");
		for(int i=0;i<usernames.length;i++){
			User user=um.getUserByUsername(usernames[i]);
			um.deleteUser(user);
		}
		return null;
	}
	public ActionForward getUserByname(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		UserManager um=(UserManager)act.getBean("userManager");
		String username = request.getParameter("username");
		User user=um.getUserByUsername(username);
		String data=user.getUsername()+","+user.getPassword()+","+user.getUsertype();
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(data);
		return null;
	}
	public ActionForward getUsers(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		UserManager um=(UserManager)act.getBean("userManager");
		String key=request.getParameter("key");
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		List<User> list=um.getUsers(key,order,sort);
		List<Object> rows=new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			User user = list.get(i); 
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("username", user.getUsername()); 
			map.put("usertype", user.getUsertype()); 
			rows.add(map); // 循环保存map到list对象this.rows中
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("rows", rows);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(jsonObj.toString());
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}
	public ActionForward getAllResource(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		CompanyMananger cm=(CompanyMananger)act.getBean("companyManager");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));// 接受参数page和rows
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		String key = request.getParameter("tran_ser_comkey");
		String tran_ser_address=request.getParameter("tran_ser_address");
		int total;
		List<Object> rows=new ArrayList<Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<Company> list=cm.getAllResources(page, row, order, sort, key,tran_ser_address);
		total=cm.getResourcesCount(key,tran_ser_address);
		for (int i = 0; i < list.size(); i++) {
			Company company = list.get(i); 
			Map<String, Object> map = new HashMap<String, Object>();
			Record record=cm.getLastRecordByCompanyId(company.getCId());
			String lasttime="";
			if(record!=null){
				lasttime=df.format(record.getRTime());
			}
			map.put("cid", company.getCId()); 
			map.put("CName", company.getCName()); // 以键值对的形式保存到map中
			map.put("CAddress", company.getCAddress());
			map.put("CLinkman", company.getCLinkman()); // 以键值对的形式保存到map中
			map.put("CPhone", company.getCPhone());
			map.put("entryuser", company.getUser().getUsername());
			map.put("CZt", this.getZt(company.getCZt()));	
			map.put("lasttime", lasttime); 
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
	public ActionForward adjuastSrc(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		CompanyMananger cm=(CompanyMananger)act.getBean("companyManager");
		User userlog=(User)request.getSession().getAttribute("user");
		String cid=request.getParameter("cid");
		String username=request.getParameter("username");
		Company company=cm.getCompanyById(cid);
		String olduser=company.getUser().getUsername();
		User user=cm.getUserObj(username);
		Record record=new Record();
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		String timeNow=df.format(ts);
		record.setRId(UUID.randomUUID().toString());
		record.setRTime(ts);
		record.setRZt(1);
		record.setRUsername(userlog.getUsername());
		record.setRTheme("资源调度");
		record.setRAbstract(company.getCName()+"于"+company.getCEntrytime()+"由"+olduser+"录入，又于"+timeNow+"由管理员"+userlog.getUsername()+
				"将该公司业务转让给"+user.getUsername()+"处理");	
		company.setUser(user);
		cm.updateCompany(company);
		record.setCompany(company);
		cm.addRecord(record);
		
		return null;
	}
	public String getZt(int CZt){
		switch(CZt){
		case 1:return "线索库";
		case 2:return "客户跟踪";
		case 3:return "意向客户";
		case 4:return "签单未到账";
		case 5:return "订单跟踪";	
		default:return "";
		}
	}
	public ActionForward checkLog(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user=(User)request.getSession().getAttribute("user");
		if(user==null){
			response.getWriter().write("err");
		}
		else{
			response.getWriter().write("ok");
		}
		return null;
	}
	public ActionForward test(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("user");
		return null;
	}
}
