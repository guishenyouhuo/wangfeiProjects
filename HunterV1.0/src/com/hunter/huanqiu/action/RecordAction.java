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

import com.hunter.huanqiu.entity.Company;
import com.hunter.huanqiu.entity.Record;
import com.hunter.huanqiu.entity.User;
import com.hunter.huanqiu.form.RecordForm;
import com.hunter.huanqiu.manager.CompanyMananger;

public class RecordAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ApplicationContext act = WebApplicationContextUtils.getWebApplicationContext(request
				.getSession().getServletContext());
		CompanyMananger cm = (CompanyMananger) act.getBean("companyManager");
		RecordForm recordForm=(RecordForm)form;
		String cid=request.getParameter("companyid");
		Record record=new Record();
		Company company=cm.getCompanyById(cid);
		String timeStr=recordForm.getRecord_time();
		Timestamp ts = Timestamp.valueOf(timeStr);
		record.setCompany(company);
		record.setRAbstract(recordForm.getRecord_abstract());
		record.setRId(UUID.randomUUID().toString());
		record.setRTheme(recordForm.getRecord_theme());
		record.setRTime(ts);
		record.setRUsername(company.getUser().getUsername());
		record.setRZt(1);
		cm.addRecord(record);
		return null;
	}

}
