package com.hunter.huanqiu.form;

import org.apache.struts.action.ActionForm;

public class CompanyForm extends ActionForm {
	private String company_name;
	private String company_address;
	private String company_linkman;
	private char company_sex;
	private String company_title;
	private String company_email;
	private String company_phone;
	
	//2014.06.19Ìí¼Ó
	private String company_telephone;
	private String company_secondlinkman;
	private String company_secondphone;
	private String company_need;
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_address() {
		return company_address;
	}
	public void setCompany_address(String company_address) {
		this.company_address = company_address;
	}
	public String getCompany_linkman() {
		return company_linkman;
	}
	public void setCompany_linkman(String company_linkman) {
		this.company_linkman = company_linkman;
	}
	public char getCompany_sex() {
		return company_sex;
	}
	public void setCompany_sex(char company_sex) {
		this.company_sex = company_sex;
	}
	public String getCompany_title() {
		return company_title;
	}
	public void setCompany_title(String company_title) {
		this.company_title = company_title;
	}
	public String getCompany_email() {
		return company_email;
	}
	public void setCompany_email(String company_email) {
		this.company_email = company_email;
	}
	public String getCompany_phone() {
		return company_phone;
	}
	public void setCompany_phone(String company_phone) {
		this.company_phone = company_phone;
	}
	//2014.06.06Ìí¼Ó
	public String getCompany_telephone() {
		return company_telephone;
	}
	public void setCompany_telephone(String company_telephone) {
		this.company_telephone = company_telephone;
	}
	public String getCompany_secondlinkman() {
		return company_secondlinkman;
	}
	public void setCompany_secondlinkman(String company_secondlinkman) {
		this.company_secondlinkman = company_secondlinkman;
	}
	public String getCompany_secondphone() {
		return company_secondphone;
	}
	public void setCompany_secondphone(String company_secondphone) {
		this.company_secondphone = company_secondphone;
	}
	public String getCompany_need() {
		return company_need;
	}
	public void setCompany_need(String company_need) {
		this.company_need = company_need;
	}

}
