package com.yashun.form;

import org.apache.struts.action.ActionForm;

public class AdLoginForm extends ActionForm {
	private String adminname;
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
	private String adminpass;

}
