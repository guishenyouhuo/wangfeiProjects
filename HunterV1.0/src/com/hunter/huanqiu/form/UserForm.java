package com.hunter.huanqiu.form;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5637965193360078723L;
	private String username;
	private String password;
	private String usertype;
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
