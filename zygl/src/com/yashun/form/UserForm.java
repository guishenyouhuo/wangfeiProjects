package com.yashun.form;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {
	String gs_name;
	public String getGs_name() {
		return gs_name;
	}
	public void setGs_name(String gs_name) {
		this.gs_name = gs_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	String password;

}
