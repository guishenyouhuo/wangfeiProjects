package com.yashun.form;

import org.apache.struts.action.ActionForm;

public class MessageForm extends ActionForm {
	private String kh_name;
	public String getKh_name() {
		return kh_name;
	}
	public void setKh_name(String kh_name) {
		this.kh_name = kh_name;
	}
	public String getKh_tel() {
		return kh_tel;
	}
	public void setKh_tel(String kh_tel) {
		this.kh_tel = kh_tel;
	}
	public String getKh_address() {
		return kh_address;
	}
	public void setKh_address(String kh_address) {
		this.kh_address = kh_address;
	}
	public String getKh_ly() {
		return kh_ly;
	}
	public void setKh_ly(String kh_ly) {
		this.kh_ly = kh_ly;
	}
	private String kh_tel;
	private String kh_address;
	private String kh_ly;

}
