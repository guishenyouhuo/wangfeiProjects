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
			SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
			String timeNow=df.format(ts);
			Record record=new Record();
			record.setRId(UUID.randomUUID().toString());
			record.setRTime(ts);
			record.setRZt(1);
			record.setRUsername(user.getUsername());
			record.setRTheme("����");
			record.setRAbstract(company.getCName()+"��"+timeNow+"��"+user.getUsername()+"����Ϊ���ٿͻ�");
			company.setCZt(2);//����״̬��Ϊ�ͻ�����״̬
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
			SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
			String timeNow=df.format(ts);
			Record record=new Record();
			record.setRId(UUID.randomUUID().toString());
			record.setRTime(ts);
			record.setRZt(1);
			record.setRUsername(user.getUsername());
			record.setRTheme("����");
			record.setRAbstract(company.getCName()+"��"+timeNow+"��"+user.getUsername()+"����Ϊ����ͻ�");
			company.setCZt(3);//����״̬��Ϊ����״̬
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
			SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
			String timeNow=df.format(ts);
			Record record=new Record();
			record.setRId(UUID.randomUUID().toString());
			record.setRTime(ts);
			record.setRZt(1);
			record.setRUsername(user.getUsername());
			record.setRTheme("����");
			record.setRAbstract(company.getCName()+"��"+timeNow+"��"+user.getUsername()+"����������ڹ����");
			company.setCZt(6);//����״̬��Ϊ����״̬
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
		int row = Integer.parseInt(request.getParameter("rows"));// ���ܲ���page��rows
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
			map.put("CName", company.getCName()); // �Լ�ֵ�Ե���ʽ���浽map��
			map.put("CAddress", company.getCAddress());
			map.put("CLinkman", company.getCLinkman());
			map.put("promoney", contract.getConMoney());
			map.put("conuptime",df.format(contract.getConTime()));
			map.put("entryuser", company.getUser().getUsername());
			map.put("lastbilltime", df.format(bill.getBillTime()));

			rows.add(map); // ѭ������map��list����this.rows��
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
			//�õ�Company
			Company company=cm.getCompanyById(cid);
			Date date = new Date();
			Timestamp ts = new Timestamp(date.getTime());
			SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
			String timeNow=df.format(ts);
			String entryTime=df.format(company.getCEntrytime());
			String theme="������";
			String abstractInfo="";
			Record record =new Record();
			record.setRId(UUID.randomUUID().toString());
			record.setRTime(ts);
			record.setRUsername(user.getUsername());
			record.setRZt(1);
			record.setRTheme(theme);
			if(company.getUser().getUsername().equals(user.getUsername())){
				abstractInfo=company.getCName()+"��"+entryTime+"��"+company.getUser().getUsername()+"¼��ϵͳ��7��δ����֮���Զ����빲��⣬����"+
				       timeNow+"���¼��뵽������";
			}
			else{
				abstractInfo=company.getCName()+"��"+entryTime+"��"+company.getUser().getUsername()+"¼��ϵͳ��7��δ����֮���Զ����빲��⣬��"+
				timeNow+"��"+user.getUsername()+"���뵽�Լ���������";
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
			fileName=formFile.getFileName();//�õ��ļ���
			fileSize=formFile.getFileSize();//�õ��ļ���С
		}
		//��ֹ�ϴ�����5M���ļ�
		if(fileSize>5*1024*1024){
			System.out.println("�ļ�����.");
			return mapping.findForward("deployerr");
		}
		InputStream is=null;
		OutputStream os=null;
		String newFileName="";
		String realPath="";
		try {
			if(!fileName.equals("")){
				//��ʼ�ϴ��ļ�
				is=formFile.getInputStream();
				//�õ�����·��
//				realPath=this.getServlet().getServletContext().getRealPath("/contracts");
				realPath="D://contracts";
				//�µ��ļ���
					newFileName=MyTools.getNewFileName(fileName);
				os=new FileOutputStream(realPath+"\\"+newFileName);
				
				int len=0;
				byte[] bytes=new byte[1024];
				while((len=is.read(bytes))>0){
					//��һ�㣬дһ��
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
		String timeNow=df.format(ts);
		Record record=new Record();
		record.setRId(UUID.randomUUID().toString());
		record.setRTime(ts);
		record.setRZt(1);
		record.setRTheme("ǩ��");
		record.setRUsername(user.getUsername());
		record.setRAbstract(company.getCName()+"��"+timeNow+"��"+user.getUsername()+"�ϴ���ͬ����Ϊǩ��δ���˿ͻ�");
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
		int row = Integer.parseInt(request.getParameter("rows"));// ���ܲ���page��rows
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
			map.put("CName", record.getCompany().getCName()); // �Լ�ֵ�Ե���ʽ���浽map��
			map.put("entryuser", record.getRUsername()); // �Լ�ֵ�Ե���ʽ���浽map��
			map.put("RTheme", record.getRTheme());
			map.put("RTime", df.format(record.getRTime()));
			map.put("RAbstract", record.getRAbstract());
			rows.add(map); // ѭ������map��list����this.rows��
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
		// ��ȡ��ǰʱ��
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
	//��ȡ�嵥
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
				str+="<tr><td style='font-size:17px;color:red;text-align:center;'><br/>"+bill.getUser().getUsername()+"��"+bill.getBillTime()+"�ϴ�����"+bill.getBillMoney()+"Ԫ���嵥��ƾ֤���£�<br/>ժҪ��"+bill.getBillAbstract()+"<br/><img src='bills/"+bill.getBillNewname()+"' /></td></tr>";
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
				//��ʼ�ϴ��ļ�
				is=new FileInputStream(file) ;
				os=new FileOutputStream(realPath+"\\"+fileName);	
				int len=0;
				byte[] bytes=new byte[1024];
				while((len=is.read(bytes))>0){
					//��һ�㣬дһ��
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
