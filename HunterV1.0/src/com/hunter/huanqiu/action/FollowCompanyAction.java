package com.hunter.huanqiu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hunter.huanqiu.entity.Bill;
import com.hunter.huanqiu.entity.Company;
import com.hunter.huanqiu.entity.Contract;
import com.hunter.huanqiu.entity.Record;
import com.hunter.huanqiu.entity.User;
import com.hunter.huanqiu.form.ContractForm;
import com.hunter.huanqiu.manager.CompanyMananger;
import com.hunter.huanqiu.utils.MyTools;

public class FollowCompanyAction extends DispatchAction {

	ApplicationContext act;
	public ActionForward followClient(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		User user=(User)request.getSession().getAttribute("user");
		if(user==null){
			return mapping.findForward("success");
		}
		String cids=request.getParameter("cid");
		String[] cidarr=cids.split(",");
		for(int i=0;i<cidarr.length;i++){
			Company company = cm.getCompanyById(cidarr[i]);
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
			String timeNow=df.format(ts);
			Record record=new Record();
			record.setRId(UUID.randomUUID().toString());
			record.setRTime(ts);
			record.setRZt(1);
			record.setRUsername(user.getUsername());
			record.setRTheme("跟进");
			record.setRAbstract(company.getCName()+"于"+timeNow+"由"+user.getUsername()+"跟进为跟踪客户");
			company.setCZt(2);//将其状态改为客户跟踪状态
			record.setCompany(company);
			cm.updateCompany(company);
			cm.addRecord(record);
		}
		return null;
	}
	public ActionForward intentClient(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		User user=(User)request.getSession().getAttribute("user");
		String cids=request.getParameter("cid");
		String[] cidarr=cids.split(",");
		for(int i=0;i<cidarr.length;i++){
			Company company = cm.getCompanyById(cidarr[i]);
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
			String timeNow=df.format(ts);
			Record record=new Record();
			record.setRId(UUID.randomUUID().toString());
			record.setRTime(ts);
			record.setRZt(1);
			record.setRUsername(user.getUsername());
			record.setRTheme("意向");
			record.setRAbstract(company.getCName()+"于"+timeNow+"由"+user.getUsername()+"跟进为意向客户");
			company.setCZt(3);//将其状态改为意向状态
			record.setCompany(company);
			cm.updateCompany(company);
			cm.addRecord(record);
		}
		return null;
	}
	/////////////////////////////////////////////////////////////////////////////
	public ActionForward giveupClient(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		User user=(User)request.getSession().getAttribute("user");
		String cids=request.getParameter("cid");
		String[] cidarr=cids.split(",");
		for(int i=0;i<cidarr.length;i++){
			Company company = cm.getCompanyById(cidarr[i]);
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
			String timeNow=df.format(ts);
			Record record=new Record();
			record.setRId(UUID.randomUUID().toString());
			record.setRTime(ts);
			record.setRZt(1);
			record.setRUsername(user.getUsername());
			record.setRTheme("放弃");
			record.setRAbstract(company.getCName()+"于"+timeNow+"被"+user.getUsername()+"放弃，存放在共享库");
			company.setCZt(6);//将其状态改为意向状态
			record.setCompany(company);
			cm.updateCompany(company);
			cm.addRecord(record);
		}
		return null;
	}
	
	////////////////////////////////////////////////////////////////////////////
	public ActionForward billFollow(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		User user=(User)request.getSession().getAttribute("user");
		int total;
		List<Object> rows;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));// 接受参数page和rows
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		String key = request.getParameter("key");
		String address_search=request.getParameter("address_search");
		String zt=request.getParameter("zt");
		rows = new ArrayList<Object>();
		List<Company> list=new ArrayList<Company>();
		if(user.getUsertype().equals("manager")){
			list = cm.getCompanys(page, row, order, sort, key,address_search,null,Integer.parseInt(zt));
			total = cm.getCompanyCount(key,address_search,null,Integer.parseInt(zt));
		}
		else{
			list = cm.getCompanys(page, row, order, sort, key,address_search,user.getUsername(),Integer.parseInt(zt));
			total = cm.getCompanyCount(key,address_search,user.getUsername(),Integer.parseInt(zt));
		}
		for (int i = 0; i < list.size(); i++) {
			Company company = list.get(i); 
			Contract contract=cm.getContractByCid(company.getCId());
			Bill bill=cm.getBillByConid(contract.getConId());
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("cid", company.getCId()); 
			map.put("conid", contract.getConId()); 
			map.put("connewname", contract.getConNewname()); 
			map.put("CName", company.getCName()); // 以键值对的形式保存到map中
			map.put("CAddress", company.getCAddress());
			map.put("CLinkman", company.getCLinkman());
			map.put("promoney", contract.getConMoney());
			map.put("conuptime",df.format(contract.getConTime()));
			map.put("entryuser", company.getUser().getUsername());
			map.put("lastbilltime", df.format(bill.getBillTime()));

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
	public ActionForward intoMineClue(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		User user=(User)request.getSession().getAttribute("user");
		String cids=request.getParameter("cid");
		String[] cidarr=cids.split(",");
		for(int i=0;i<cidarr.length;i++){
			String cid=cidarr[i];		
			//得到Company
			Company company=cm.getCompanyById(cid);
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
			String timeNow=df.format(ts);
			String entryTime=df.format(company.getCEntrytime());
			String theme="线索化";
			String abstractInfo="";
			Record record =new Record();
			record.setRId(UUID.randomUUID().toString());
			record.setRTime(ts);
			record.setRUsername(user.getUsername());
			record.setRZt(1);
			record.setRTheme(theme);
			if(company.getUser().getUsername().equals(user.getUsername())){
				abstractInfo=company.getCName()+"于"+entryTime+"由"+company.getUser().getUsername()+"录入系统，7天未处理之后自动进入共享库，又于"+
				       timeNow+"重新加入到线索库";
			}
			else{
				abstractInfo=company.getCName()+"于"+entryTime+"由"+company.getUser().getUsername()+"录入系统，7天未处理之后自动进入共享库，于"+
				timeNow+"由"+user.getUsername()+"加入到自己的线索库";
				company.setUser(user);
			}
			company.setCZt(1);
			record.setRAbstract(abstractInfo);
			record.setCompany(company);
			cm.updateCompany(company);
			cm.addRecord(record);
		}
		return null;
	}
	public ActionForward getCount(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		String zt=request.getParameter("zt");
		User user=(User)request.getSession().getAttribute("user");
		int count=cm.getCountByZtAndUser(zt, user.getUsername());
		if(user.getUsertype().equals("manager")){
			response.getWriter().write("0");
		}
		else{
			response.getWriter().write(count+"");
		}
		return null;
	}
	public ActionForward upContract(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		ContractForm contractForm=(ContractForm)actionform;
		User user=(User)request.getSession().getAttribute("user");
		if(user==null){
			return mapping.findForward("success");
		}
		String fileName="";
		long fileSize=0;
		FormFile formFile =contractForm.getContractname();;
		if(formFile!=null){
			fileName=formFile.getFileName();//得到文件名
			fileSize=formFile.getFileSize();//得到文件大小
		}
		//禁止上传大于5M的文件
		if(fileSize>5*1024*1024){
			System.out.println("文件过大.");
			return mapping.findForward("deployerr");
		}
		InputStream is=null;
		OutputStream os=null;
		String newFileName="";
		String realPath="";
		try {
			if(!fileName.equals("")){
				//开始上传文件
				is=formFile.getInputStream();
				//得到绝对路径
//				realPath=this.getServlet().getServletContext().getRealPath("/contracts");
				realPath="D://contracts";
				//新的文件名
					newFileName=MyTools.getNewFileName(fileName);
				os=new FileOutputStream(realPath+"\\"+newFileName);
				
				int len=0;
				byte[] bytes=new byte[1024];
				while((len=is.read(bytes))>0){
					//读一点，写一点
					os.write(bytes, 0, len);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			try {
				if(os!=null)
					os.close();
				if(is!=null)
					is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Company company=cm.getCompanyById(contractForm.getBelongcid());
		
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		String timeNow=df.format(ts);
		Record record=new Record();
		record.setRId(UUID.randomUUID().toString());
		record.setRTime(ts);
		record.setRZt(1);
		record.setRTheme("签单");
		record.setRUsername(user.getUsername());
		record.setRAbstract(company.getCName()+"于"+timeNow+"由"+user.getUsername()+"上传合同，成为签单未到账客户");
		record.setCompany(company);
		company.setCZt(4);
		cm.updateCompany(company);
		cm.addRecord(record);
		Contract contract=new Contract();
		contract.setConId(UUID.randomUUID().toString());
		contract.setConNewname(newFileName);
		contract.setCompany(company);
		contract.setConName(fileName);
		contract.setConTime(ts);
		contract.setConMoney(contractForm.getContractmoney());
		contract.setConZt(1);
		cm.addContract(contract);
		return null;
	}
	public ActionForward getRecord(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));// 接受参数page和rows
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		String cid=request.getParameter("cid");
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		List<Object> rows = new ArrayList<Object>();
		List<Record> list=cm.getRecordByCid(page, row, order, sort, cid); 
		int total=cm.getRecordCountByCid(page, row, order, sort, cid);
		for(int i=0;i<list.size();i++){
			Record record=list.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("RId", record.getRId()); 
			map.put("CName", record.getCompany().getCName()); // 以键值对的形式保存到map中
			map.put("entryuser", record.getRUsername()); // 以键值对的形式保存到map中
			map.put("RTheme", record.getRTheme());
			map.put("RTime", df.format(record.getRTime()));
			map.put("RAbstract", record.getRAbstract());
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
	public ActionForward addNewProject(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		User user=(User)request.getSession().getAttribute("user");
		String cid=request.getParameter("cid");
		Company oldcompany=cm.getCompanyById(cid);
		Company company=new Company();
		// 获取当前时间
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		company.setCId(UUID.randomUUID().toString());
		company.setCEntrytime(ts);
		company.setCZt(1);
		company.setCAddress(oldcompany.getCAddress());
		company.setCEmail(oldcompany.getCEmail());
		company.setCLinkman(oldcompany.getCLinkman());
		company.setCName(oldcompany.getCName());
		company.setCTitle(oldcompany.getCTitle());
		company.setCPhone(oldcompany.getCPhone());
		company.setCSex(oldcompany.getCSex());
		company.setUser(user);
		cm.addCompany(company);
		return null;
	}
	//获取清单
	public ActionForward getBillLists(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		String conid = request.getParameter("conid");
		response.setCharacterEncoding("utf-8");
		String realPath=this.getServlet().getServletContext().getRealPath("/bills");
		String str="<table style='width:100%;'>";
		List<Bill> list=cm.getBillsByConid(conid);
		if(list.size()==0){
			response.getWriter().write("0");
		}
		else{
			for(int i=0;i<list.size();i++){
				Bill bill=list.get(i);
				File file=new File(realPath+"\\"+bill.getBillNewname());
				File fileInDisk=new File("D://bills//"+bill.getBillNewname());
				if(!file.exists()){
					this.upLoadFile(fileInDisk, bill.getBillNewname(), realPath);
				}
				str+="<tr><td style='font-size:17px;color:red;text-align:center;'><br/>"+bill.getUser().getUsername()+"于"+bill.getBillTime()+"上传付款"+bill.getBillMoney()+"元的清单，凭证如下：<br/>摘要："+bill.getBillAbstract()+"<br/><img src='bills/"+bill.getBillNewname()+"' /></td></tr>";
			}
			str+="</table>";
			response.getWriter().write(str);	
		}
		return null;
	}	
	public void upLoadFile(File file,String fileName,String realPath){
		InputStream is=null;
		OutputStream os=null;
		try {
			if(!fileName.equals("")){
				//开始上传文件
				is=new FileInputStream(file) ;
				os=new FileOutputStream(realPath+"\\"+fileName);	
				int len=0;
				byte[] bytes=new byte[1024];
				while((len=is.read(bytes))>0){
					//读一点，写一点
					os.write(bytes, 0, len);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			try {
				if(os!=null)
					os.close();
				if(is!=null)
					is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
