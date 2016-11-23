package com.hunter.huanqiu.manager;

import java.util.List;

import com.hunter.huanqiu.entity.*;
import com.hunter.huanqiu.service.inter.*;
import com.sun.org.apache.bcel.internal.generic.ATHROW;

public class EmployeeManager {
	
	private EmployeeServiceInter employeeService;
	private ResumeServiceInter resumeService;
	private RecordServiceInter recordService;
	
	public RecordServiceInter getRecordService() {
		return recordService;
	}
	public void setRecordService(RecordServiceInter recordService) {
		this.recordService = recordService;
	}
	public EmployeeServiceInter getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeServiceInter employeeService) {
		this.employeeService = employeeService;
	}

	public ResumeServiceInter getResumeService() {
		return resumeService;
	}
	public void setResumeService(ResumeServiceInter resumeService) {
		this.resumeService = resumeService;
	}
	/*
	public List<Employee> getEmployees(String username,int page,int row,String order,String sort,String key_ename,String key_etype,String key_eaddress,int zt){
		
		String hql="from Employee as n where EZt="+zt;
		if(key_ename!=null){
			hql=hql+" and n.EName like'%"+key_ename+"%'";
		}
		if(key_etype!=null){
			hql=hql+" and n.ECertype like'%"+key_etype+"%'";
		}
		if(key_eaddress!=null){
			hql=hql+" and n.EAddress like'%"+key_eaddress+"%'";
		}
		if(username!=null){
			hql=hql+" and n.user.username='"+username+"'";
		}
		if(order!=null && sort!=null){
			hql=hql+" order by "+sort+" "+order;
		}
		@SuppressWarnings("unchecked")
		List<Employee> list=employeeService.search(hql, null,page,row);
		return list;
		
		
	}
	*/
	////////////////////////////////////////////////////////////////////////////////
public List<Employee> getEmployees(String username,int page,int row,String order,String sort,String key_ename,String key_etype,String key_eaddress,int zt)
{
		String hql="from Employee as n where EZt="+zt;
		if(key_ename!=null){
			hql=hql+" and n.EName like'%"+key_ename+"%'";
		}
		if(key_etype!=null){
			hql=hql+" and n.ECertype like'%"+key_etype+"%'";
		}
		if(key_eaddress!=null){
			hql=hql+" and n.EAddress like'%"+key_eaddress+"%'";
		}
		if(username!=null){
			hql=hql+" and n.user.username='"+username+"'";
		}
		if(order!=null && sort!=null){
			hql=hql+" order by "+sort+" "+order;
		}
		@SuppressWarnings("unchecked")
		List<Employee> list=employeeService.search(hql, null,page,row);
		return list;
		
		
	}
	////////////////////////////////////////////////////////////////////////////////
public List<Employee> getEmployees_allother(String username,int page,int row,String order,String sort,String key_ename,String key_etype,String key_eaddress)
{
		
		String hql="";
		if(username!=null){
			hql="from Employee as n where n.user.username not like'%"+username+"%' and n.EZt!=0";
			if(key_ename!=null){
				hql=hql+" and n.EName like'%"+key_ename+"%'";
			}
			if(key_etype!=null){
				hql=hql+" and n.ECertype like'%"+key_etype+"%'";
			}
			if(key_eaddress!=null){
				hql=hql+" and n.EAddress like'%"+key_eaddress+"%'";
			}
			@SuppressWarnings("unchecked")
			List<Employee> list=employeeService.search(hql, null,page,row);
			return list;
		}
		else
			return null;
	
}
public int getEmpOtherAllCount(String username,String key_ename,String key_etype,String key_eaddress){
	if(key_ename==null){
		key_ename="";
	}
	if(key_etype==null){
		key_etype="";
	}
	if(key_eaddress==null){
		key_eaddress="";
	}
	String hql="select count(*) from Employee e where e.EName like'%"+key_ename+"%' and e.EZt!=0 and e.ECertype like '%"+key_etype+"%'and e.EAddress like '%"+key_eaddress+"%'";
	if(username!=null){
		hql=hql+" and e.user.username!='"+username+"'";
	}
	return employeeService.getCount(hql);
}
	////////////////////////////////////////////////////////////////////////////////
	public int getEmployeeCount(String username,String key_ename,String key_etype,String key_eaddress,int zt){
		
		if(key_ename==null){
			key_ename="";
		}
		if(key_etype==null){
			key_etype="";
		}
		if(key_eaddress==null){
			key_eaddress="";
		}
		String hql="select count(*) from Employee e where e.EName like'%"+key_ename+"%' and e.EZt="+zt+" and e.ECertype like '%"+key_etype+"%'and e.EAddress like '%"+key_eaddress+"%'";
		if(username!=null){
			hql=hql+" and e.user.username='"+username+"'";
		}
		return employeeService.getCount(hql);
	}
	public Employee getEmployeeById(String eid){
		
		//System.out.println("EmployeeManager中的getEmployeeById方法获得的EId:"+eid);
		String hql="from Employee where EId='"+eid+"'";
		List<Employee> list=employeeService.search(hql, null);
		Employee employee=list.get(0);
		return employee;
	}
	
	//根据求职者id获取简历
	
	public Resume getResumeByEid(String eid){
		
		//System.out.println("EmployeeManager中的getResumeByEid方法获得的EId:"+eid);
		String hql="from Resume as r where r.employee.EId=? and r.resZt=1 order by resUptime desc";
		String[] parameters={eid};
		List<Resume> list = resumeService.search(hql, parameters);
		Resume resume=null;
		if(list.size()>0){
			resume=list.get(0);
		}
		return resume;
	}

	public void addEmployee(Employee employee){
		employeeService.save(employee);
	}

	public void addResume(Resume resume){
		resumeService.save(resume);
	}

	public void updateResume(Resume resume){
		resumeService.update(resume);
	}
	public void deleteResume(Resume resume){
		resumeService.delete(resume);
	}
	
	public void updateEmployee(Employee employee){
		employeeService.update(employee);
	}
	public void deleteEmployee(Employee employee){
		employeeService.delete(employee);
	}
	
	public void addRecord(Re_record record){//增加跟进记录
		recordService.save(record);
	}
	
	public List<Re_record> getRecordByEid(int page,int row,String order,String sort,String eid){
		String hql="from Re_record as r where r.employee.EId=? and r.RZt=1";
		if(order!=null && sort!=null){
			hql=hql+" order by "+sort+" "+order;
		}
		String[] parameter={eid};
		List<Re_record> list=recordService.search(hql, parameter,page,row);
		return list;
	}
	public int getRecordCountByEid(int page,int row,String order,String sort,String eid){
		String hql="select count(*) from Re_record as r where  r.employee.EId='"+eid+"' and r.RZt=1";
		if(order!=null && sort!=null){
			hql=hql+" order by "+sort+" "+order;
		}
		int count=recordService.getCount(hql);
		return count;		
	}

}
