package com.yashun.form;

import org.apache.struts.action.ActionForm;

public class AdminForm extends ActionForm{
	String adminname;
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getAdminpass() {
		return adminpass;
	}
	public void setAdminpass(String adminpass) {
		this.adminpass = adminpass;
	}
	public String getVar_gs() {
		return var_gs;
	}
	public void setVar_gs(String var_gs) {
		this.var_gs = var_gs;
	}
	String adminpass;
	String var_gs;

}
