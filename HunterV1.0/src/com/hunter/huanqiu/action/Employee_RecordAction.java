package com.hunter.huanqiu.action;

import java.sql.Timestamp;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import com.hunter.huanqiu.entity.Employee;
import com.hunter.huanqiu.entity.Re_record;
import com.hunter.huanqiu.entity.User;
import com.hunter.huanqiu.form.RecordForm;
import com.hunter.huanqiu.manager.EmployeeManager;

public class Employee_RecordAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		EmployeeManager em = (EmployeeManager) act.getBean("employeeManager");
		RecordForm recordForm=(RecordForm)form;
		String cid=request.getParameter("employeeid");
		System.out.println("******"+cid);
		Re_record record=new Re_record();
		Employee employee=em.getEmployeeById(cid);
		String timeStr=recordForm.getRecord_time();
		System.out.println("******"+recordForm.getRecord_time());
		Timestamp ts = Timestamp.valueOf(timeStr);
		//System.out.println("employee:"+employee.getEId());
		//System.out.println("abstract:"+recordForm.getRecord_abstract());
		record.setEmployee(employee);
		record.setRAbstract(recordForm.getRecord_abstract());
		record.setRId(UUID.randomUUID().toString());
		record.setRTheme(recordForm.getRecord_theme());
		record.setRTime(ts);
		record.setRUsername(employee.getUser().getUsername());
		record.setRZt(1);
		em.addRecord(record);
		return null;
	}

}
