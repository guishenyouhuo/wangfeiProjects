package com.hunter.huanqiu.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.actions.MappingDispatchAction;
import org.apache.struts.upload.FormFile;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import com.hunter.huanqiu.entity.*;
import com.hunter.huanqiu.form.*;
import com.hunter.huanqiu.manager.*;
import com.hunter.huanqiu.utils.MyTools;

public class EmployeeAction extends DispatchAction {
	
	ApplicationContext act;

	public ActionForward addEmployee(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		EmployeeManager pm = (EmployeeManager) act.getBean("employeeManager");
		EmployeeForm employeeForm = (EmployeeForm) actionForm;
		Employee employee = new Employee();
		User user = (User) request.getSession().getAttribute("user");
		//System.out.println("username:"+user.getUsername());
		//��ȡ����ʱ��
		Date date = new Date();
		//System.out.println("��ǰʱ��:"+date.getTime());
		Timestamp ts = new Timestamp(date.getTime());
		
		employee.setEId(UUID.randomUUID().toString());
		employee.setESex(employeeForm.getEmployee_sex());
		employee.setEName(employeeForm.getEmployee_name());
		employee.setEPhone(employeeForm.getEmployee_phone());
		employee.setEEmail(employeeForm.getEmployee_email());
		employee.setEAddress(employeeForm.getEmployee_address());
		employee.setECertype(employeeForm.getEmployee_certype());
		employee.setECerstate(employeeForm.getEmployee_cerstate());
		employee.setEZt(1);
		employee.setUser(user);
		employee.setEEntrydate(ts);
		pm.addEmployee(employee);//��ְ����Ϣ���
		
		/////////////////////////////////�����ϴ�����////////////////////////////
		FormFile formFile =employeeForm.getEmployee_resume();
		if(formFile==null)
		{
			System.out.println("**************************δ�ϴ�����***************************");
		}
		if(formFile!=null)
		{
		String fileName="";
		long fileSize=0;
		
		if(formFile!=null){
			fileName=formFile.getFileName();//�õ��ļ���
			fileSize=formFile.getFileSize();//�õ��ļ���С
		}
		//��ֹ�ϴ�����5M���ļ�
		if(fileSize>5*1024*1024){
			System.out.println("�ļ�����.");
			return actionMapping.findForward("deployer");
		}
		InputStream is=null;//������
		OutputStream os=null;
		String newFileName="";//�ļ���
		String realPath="";
		try {
			if(!fileName.equals("")){
				//��ʼ�ϴ��ļ�
				is=formFile.getInputStream();
				//�µ��ļ���
				newFileName=MyTools.getNewFileName(fileName);
				//System.out.println("���ϴ�����newFileName��"+newFileName);
				//�����ϴ��ļ�Ҫ�����·��
				os=new FileOutputStream("D:/resumes/"+newFileName);  
				//os=new FileOutputStream(realPath+"/"+newFileName);
				
				int len=0;
				byte[] bytes=new byte[1024*1024];
				while((len=is.read(bytes))>0){
					//��һ�㣬дһ��
					os.write(bytes, 0, len);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally{
			try {
				if(os!=null)
					os.close();
				if(is!=null)
					is.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		Resume resume=new Resume();
		resume.setResId(UUID.randomUUID().toString());
		resume.setEmployee(employee);
		resume.setResName(newFileName);
		resume.setResOriname(fileName);
		resume.setResUptime(ts);
		resume.setResZt(1);
		//System.out.println("Id:"+resume.getResId()+"\nName:"+resume.getResName()+"\nEmployee:"+resume.getEmployee().getEName()+"\nTime:"+resume.getResUptime());
		////////////////////////////////////////////////////////////////
	
	
	    pm.addResume(resume);//��ְ����Ϣ���
		}
		return actionMapping.findForward("success");
	}

	////////////////////////////////////////////////////////////////////////////////
	public ActionForward editEmployee(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		EmployeeManager pm = (EmployeeManager) act.getBean("employeeManager");
		
		String EId=request.getParameter("eid");
		//System.out.println("editEmployee�е�EID:"+EId);
		
		Employee employee=pm.getEmployeeById(EId);
		
		Resume resume=pm.getResumeByEid(EId);
		String filePath;
		if(resume!=null)
		{
		System.out.println("�����������ƣ�"+resume.getResOriname());
		//String filePath=resume.getResOriname();
		filePath=resume.getResOriname();
		//System.out.println("filePath:"+filePath);
		}
		else
		{
			filePath="";
		}
		String data=employee.getEName()+","+employee.getESex()+","+employee.getEPhone()+","
				+employee.getEAddress()+","+employee.getEEmail()+","+employee.getECertype()+","+employee.getECerstate()+","+employee.getEId()+","+filePath;
		//System.out.println("data:"+data);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(data);
		return null;
		
	}
	
	public ActionForward updateEmployee(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		String serverPath=this.getServlet().getServletContext().getRealPath("/resumehtmls");
		EmployeeManager em = (EmployeeManager) act.getBean("employeeManager");
		User user = (User) request.getSession().getAttribute("user");
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		
		String eid=request.getParameter("eid");
		//System.out.println("updateEmployee�л�õ�EId:"+eid);

		Employee employee = em.getEmployeeById(eid);

		
		EmployeeForm employeeForm = (EmployeeForm) actionForm;
		employee.setEName(employeeForm.getEmployee_name());
		employee.setESex(employeeForm.getEmployee_sex());
		employee.setEPhone(employeeForm.getEmployee_phone());
		employee.setEAddress(employeeForm.getEmployee_address());
		employee.setEEmail(employeeForm.getEmployee_email());
		employee.setECertype(employeeForm.getEmployee_certype());
		employee.setECerstate(employeeForm.getEmployee_cerstate());
		em.updateEmployee(employee);
		
	/*****************************************************************************************/
		Resume resume=em.getResumeByEid(eid);//��ѯ���ݿ⣬�ж��Ƿ��Ѿ������ϴ�����
		
		FormFile formFile =employeeForm.getEmployee_resume();//����ȡ���ж��Ƿ��ϴ��ļ�
		if(formFile==null)//��û���ϴ�
		{
			System.out.println("**************************δ�ϴ�����***************************");
		}
		if(formFile!=null)//���ϴ�
		{
			String fileName="";
			long fileSize=0;
			fileName=formFile.getFileName();//�õ��ļ���
			fileSize=formFile.getFileSize();//�õ��ļ���С
			//��ֹ�ϴ�����5M���ļ�
			if(fileSize>5*1024*1024)
			{
				System.out.println("�ļ�����.");
				return actionMapping.findForward("deployer");
			}
			InputStream is=null;//������
			OutputStream os=null;
			String newFileName="";//�ļ���
			String realPath="";
			try 
			{
				if(!fileName.equals(""))
				{
					//��ʼ�ϴ��ļ�
					is=formFile.getInputStream();
					
					if(resume!=null)//������ݿ��д��ڼ�¼,��ɾ�����doc�ļ���html�ļ��Լ��ļ���������ļ�
					{
						String resumeResName=resume.getResName();
						File resumeDoc=new File("D://resumes//"+resumeResName);//doc/docx�ļ�
						File resumeHtml=new File(serverPath+"//"+resumeResName.substring(0, resumeResName.indexOf("."))+".html");//html�ļ�
						File resumes=new File(serverPath+"//"+resumeResName.substring(0, resumeResName.indexOf("."))+".files");//�ļ���
						if(resumes.exists())
						{
							if(resumes.isDirectory())
							{
								File files[] = resumes.listFiles();               //����Ŀ¼�����е��ļ� files[];
								for(int j=0;j<files.length;j++)
								{            //����Ŀ¼�����е��ļ�
									files[j].delete();          //��ÿ���ļ� ������������е���
								}
								resumes.delete();
							}
							//System.out.println("ɾ���ļ��гɹ�");
						}
						if(resumeHtml.exists())
						{
							resumeHtml.delete();
							//System.out.println("ɾ��html�ļ��ɹ�");
						}
						if(resumeDoc.exists())
						{
							resumeDoc.delete();
							//System.out.println("ɾ��doc�ļ��ɹ�");
						}
					}
					//�������ݿ����Ƿ���ڼ�¼������Ҫ���ļ���������
					//�µ��ļ���
					newFileName=MyTools.getNewFileName(fileName);
					//�����ϴ��ļ�Ҫ�����·��
					os=new FileOutputStream("D:/resumes/"+newFileName);  
					int len=0;
					byte[] bytes=new byte[1024*1024];
					while((len=is.read(bytes))>0)
					{
						//��һ�㣬дһ��
						os.write(bytes, 0, len);
					}
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			finally
			{
				try 
				{
					if(os!=null)
						os.close();
					if(is!=null)
						is.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}

		
		if(resume==null)//���ݿ��в����ڼ�����¼
		{
			Resume updateresume=new Resume();
			updateresume.setResId(UUID.randomUUID().toString());
			updateresume.setEmployee(employee);
			updateresume.setResName(newFileName);
			updateresume.setResOriname(fileName);
			updateresume.setResUptime(ts);
			updateresume.setResZt(1);
			em.addResume(updateresume);//��ְ����Ϣ���
		}
		//System.out.println("Id:"+resume.getResId()+"\nName:"+resume.getResName()+"\nEmployee:"+resume.getEmployee().getEName()+"\nTime:"+resume.getResUptime());
		////////////////////////////////////////////////////////////////
		else
		{
			resume.setResOriname(fileName);
			resume.setResName(newFileName);
			resume.setResUptime(ts);
			em.updateResume(resume);
	
		}
	
	   
		}
		
		
		
		
		
		
	/******************************************************************************************/

		return actionMapping.findForward("success");
	}
	
	public void employeeCl(HttpServletResponse response,HttpServletRequest request,ActionForm actionForm,Employee employee) throws Exception{	
		EmployeeForm employeeForm = (EmployeeForm) actionForm;
		//System.out.println("employeeForm"+employeeForm.getEmployee_name());
		// ��װemployee��
		employee.setEName(employeeForm.getEmployee_name());
		employee.setESex(employeeForm.getEmployee_sex());
		employee.setEPhone(employeeForm.getEmployee_phone());
		employee.setEAddress(employeeForm.getEmployee_address());
		employee.setEEmail(employeeForm.getEmployee_email());
		employee.setECertype(employeeForm.getEmployee_certype());
		employee.setECerstate(employeeForm.getEmployee_cerstate());
		//company.setUser(user);
		//company.setCZt(1);
	}
	////////////////////////////////////////////////////////////////////////////////
	
	public ActionForward deleteEmployee(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		String serverPath=this.getServlet().getServletContext().getRealPath("/resumehtmls");
		EmployeeManager em = (EmployeeManager) act.getBean("employeeManager");
		String eid=request.getParameter("eid");
		String[] eids=eid.split(",");
		
		for(int i=0;i<eids.length;i++){
			//System.out.println("index"+i+":"+eids[i]);
			Employee employee=em.getEmployeeById(eids[i]);
			employee.setEZt(0);
			em.updateEmployee(employee);
			
			/***********************************************************/
			Resume resume_del=em.getResumeByEid(eids[i]);
			//System.out.println("***"+resume_del+"***");
			if(resume_del!=null){
				String resumeResName=resume_del.getResName();
				File resumeDoc=new File("D://resumes//"+resumeResName);//doc/docx�ļ�
				//System.out.println("serverPath·��:"+serverPath);
				File resumeHtml=new File(serverPath+"//"+resumeResName.substring(0, resumeResName.indexOf("."))+".html");//html�ļ�
				File resumes=new File(serverPath+"//"+resumeResName.substring(0, resumeResName.indexOf("."))+".files");//�ļ���
				if(resumes.exists()){
					if(resumes.isDirectory())
					{
						 File files[] = resumes.listFiles();               //����Ŀ¼�����е��ļ� files[];
					     for(int j=0;j<files.length;j++)
					     {            //����Ŀ¼�����е��ļ�
					      files[j].delete();          //��ÿ���ļ� ������������е���
					     }
					     resumes.delete();
					}
					//System.out.println("ɾ���ļ��гɹ�");
				}
				if(resumeHtml.exists()){
					resumeHtml.delete();
					//System.out.println("ɾ��html�ļ��ɹ�");
				}
				if(resumeDoc.exists()){
					resumeDoc.delete();
					//System.out.println("ɾ��doc�ļ��ɹ�");
				}
				resume_del.setResZt(0);
				em.updateResume(resume_del);
				//System.out.println("ɾ����������ļ��ɹ�");
			}

		}
		return null;
	}
////////////////////////////////////////////////////////////////////////////////////

	public ActionForward allocateEmployee(ActionMapping actionMapping,//�����Ƽ�
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		EmployeeManager em = (EmployeeManager) act.getBean("employeeManager");
		String eid=request.getParameter("eid");
		String[] eids=eid.split(",");
		for(int i=0;i<eids.length;i++){
			Employee employee=em.getEmployeeById(eids[i]);
			employee.setEZt(2);
			em.updateEmployee(employee);
		}
		return null;
	}
	
//////////////////////////////////////////////////////////////////////////////////
	
	public ActionForward intentEmployee(ActionMapping actionMapping,//�����˲�
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		EmployeeManager em = (EmployeeManager) act.getBean("employeeManager");
		String eid=request.getParameter("eid");
		String[] eids=eid.split(",");
		for(int i=0;i<eids.length;i++){
			Employee employee=em.getEmployeeById(eids[i]);
			employee.setEZt(3);
			em.updateEmployee(employee);
		}
		return null;
	}
	
//////////////////////////////////////////////////////////////////////////////////
	
public ActionForward markEmployee(ActionMapping actionMapping,//��ǩ�˲�
ActionForm actionForm, HttpServletRequest request,
HttpServletResponse response) throws Exception {
act = WebApplicationContextUtils.getWebApplicationContext(request
.getSession().getServletContext());
EmployeeManager em = (EmployeeManager) act.getBean("employeeManager");
String eid=request.getParameter("eid");
String[] eids=eid.split(",");
for(int i=0;i<eids.length;i++){
Employee employee=em.getEmployeeById(eids[i]);
employee.setEZt(4);
em.updateEmployee(employee);
}
return null;
}
/////////////////////////////////////////////////////////////////////////
public ActionForward getRecord(ActionMapping mapping, ActionForm actionform,
		HttpServletRequest request, HttpServletResponse response) throws Exception {

	act = WebApplicationContextUtils.getWebApplicationContext(request
			.getSession().getServletContext());
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	int page = Integer.parseInt(request.getParameter("page"));
	int row = Integer.parseInt(request.getParameter("rows"));// ���ܲ���page��rows
	String order = request.getParameter("order");
	String sort = request.getParameter("sort");
	String eid=request.getParameter("eid");
	System.out.println("EID:"+eid);
	EmployeeManager em = (EmployeeManager) act.getBean("employeeManager");
	List<Object> rows = new ArrayList<Object>();
	List<Re_record> list=em.getRecordByEid(page, row, order, sort, eid); 
	int total=em.getRecordCountByEid(page, row, order, sort, eid);
	System.out.println("list.size:"+list.size());
	for(int i=0;i<list.size();i++){
		Re_record record=list.get(i);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("RId", record.getRId());
		map.put("EName", record.getEmployee().getEName());
		map.put("entryuser", record.getRUsername()); // �Լ�ֵ�Ե���ʽ���浽map��
		map.put("RTheme", record.getRTheme());
		map.put("RTime", df.format(record.getRTime()));
		map.put("RAbstract", record.getRAbstract());
		rows.add(map); // ѭ������map��list����this.rows��
	}
	System.out.println("rows.size:"+rows.size());
	JSONObject jsonObj = new JSONObject();
	jsonObj.put("rows", rows);
	jsonObj.put("total", total);
	response.setCharacterEncoding("utf-8");
	response.getWriter().write(jsonObj.toString());
	response.getWriter().flush();
	response.getWriter().close();
	return null;
}
///////////////////////////////////////////////////////////////////////
	public ActionForward showEmployeeList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		EmployeeManager em = (EmployeeManager) act.getBean("employeeManager");
		int page = Integer.parseInt(request.getParameter("page"));
		int row = Integer.parseInt(request.getParameter("rows"));// ���ܲ���page��rows
		
		String order = request.getParameter("order");
		String sort = request.getParameter("sort");
		String key_ename = request.getParameter("key_ename");
		String key_etype = request.getParameter("key_etype");
		String key_eaddress = request.getParameter("key_eaddress");
		
		String employee_zt=request.getParameter("zt");
		User user=(User)request.getSession().getAttribute("user");
		int total=0;
		List<Object> rows=new ArrayList<Object>();
		List<Employee> list =new ArrayList<Employee>();
		if(user.getUsertype().equals("manager")){
			list = em.getEmployees(null,page, row, order, sort, key_ename,key_etype,key_eaddress,Integer.parseInt(employee_zt));
			total = em.getEmployeeCount(null,key_ename,key_etype,key_eaddress,Integer.parseInt(employee_zt));
		}
		else{
			list = em.getEmployees(user.getUsername(),page, row, order, sort, key_ename,key_etype,key_eaddress,Integer.parseInt(employee_zt));
			total = em.getEmployeeCount(user.getUsername(),key_ename,key_etype,key_eaddress,Integer.parseInt(employee_zt));
		}
		for (int i = 0; i < list.size(); i++) {
			Employee employee = list.get(i); // ��ԭʼѭ����������employee����
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			//System.out.println("employee.getEId():"+employee.getEId());
			Resume resume=em.getResumeByEid(employee.getEId());
			//System.out.println("******************"+resume);
			map.put("eid", employee.getEId());//��ȡ������ID
			map.put("EName", employee.getEName()); // �Լ�ֵ�Ե���ʽ���浽map��
			if(employee.getESex().charAt(0)=='m')
			{	
				String sex="��";
				map.put("ESex",sex);
			}
			else
			{
				String sex="Ů";
				map.put("ESex",sex);
			}
			map.put("EPhone", employee.getEPhone());
			map.put("ECertype",employee.getECertype());
			map.put("ECerstate", employee.getECerstate());
			map.put("EAddress",employee.getEAddress());
			map.put("EEntryuser", employee.getUser().getUsername());
			map.put("EEntrydate", df.format(employee.getEEntrydate()));
			if(resume!=null)
			{
				
			map.put("EResume_name", resume.getResName());//
			map.put("EResume_boolean", "��");
			//System.out.println("********EResume_name:"+resume.getResName());
			}
			else
			{
				map.put("EResume_name", "");
				map.put("EResume_boolean", "��");
			}
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

///////////////////////////////////////////////////////////////////////////////////
public ActionForward showEmployeeList_allother(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	act = WebApplicationContextUtils.getWebApplicationContext(request
			.getSession().getServletContext());
	EmployeeManager em = (EmployeeManager) act.getBean("employeeManager");
	int page = Integer.parseInt(request.getParameter("page"));
	int row = Integer.parseInt(request.getParameter("rows"));// ���ܲ���page��rows
	String order = request.getParameter("order");
	String sort = request.getParameter("sort");
	String key_ename = request.getParameter("key_ename");
	String key_etype = request.getParameter("key_etype");
	String key_eaddress = request.getParameter("key_eaddress");
	
	//String employee_zt=request.getParameter("zt");
	User user=(User)request.getSession().getAttribute("user");
	int total=0;
	List<Object> rows=new ArrayList<Object>();
	List<Employee> list =new ArrayList<Employee>();
	if(user.getUsertype().equals("manager")){
		//list = em.getEmployees_allother(null,page, row, order, sort, key_ename);
		//list = em.getEmployees_allother(null,page, row, order, sort, key_ename,key_etype,key_eaddress);
		//total = em.getEmpOtherAllCount(user.getUsername(),key_ename,key_etype,key_eaddress);
	}
	else{
		list = em.getEmployees_allother(user.getUsername(),page, row, order, sort, key_ename,key_etype,key_eaddress);
		total = em.getEmpOtherAllCount(user.getUsername(),key_ename,key_etype,key_eaddress);
	}
	System.out.println(total+"sdsdds");
	if(list!=null)
	{
		for (int i = 0; i < list.size(); i++)
		{
			Employee employee = list.get(i); // ��ԭʼѭ����������employee����
			Map<String, Object> map = new HashMap<String, Object>();
			Resume resume=em.getResumeByEid(employee.getEId());
			map.put("eid", employee.getEId());//��ȡ������ID
			map.put("EName", employee.getEName()); // �Լ�ֵ�Ե���ʽ���浽map��
			if(employee.getESex().charAt(0)=='m')
			{	
				String sex="��";
				map.put("ESex",sex);
			}
			else
			{
				String sex="Ů";
				map.put("ESex",sex);
			}
			/////////////////////////////////////////////////////////////////////////////
			map.put("EPhone", "");
			/////////////////////////////////////////////////////////////////////////////
			map.put("ECertype",employee.getECertype());
			map.put("ECerstate", employee.getECerstate());
			map.put("EAddress",employee.getEAddress());
			map.put("EEntryuser", employee.getUser().getUsername());
			map.put("EEntrydate", df.format(employee.getEEntrydate()));
			if(resume!=null)
			{
				map.put("EResume_name", resume.getResName());//
				map.put("EResume_boolean", "��");
				//System.out.println("********EResume_name:"+resume.getResName());
			}
			else
			{
				map.put("EResume_name", "");
				map.put("EResume_boolean", "��");
			}
			rows.add(map); // ѭ������map��list����this.rows��
		}
	}
	else
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eid", "");
		map.put("EName", "");
		map.put("ESex","");
		map.put("EPhone", "");
		map.put("ECertype","");
		map.put("ECerstate", "");
		map.put("EAddress","");
		map.put("EEntryUser", "");
		map.put("EEntrydate", "");
		map.put("EResume_name", "");//
		map.put("EResume_boolean", "");
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

}

