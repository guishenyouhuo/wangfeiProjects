package com.hunter.huanqiu.form;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class EmployeeForm extends ActionForm {

	private String employee_name;
	private String employee_sex;
	private String employee_phone;
	private String employee_address;
	private String employee_email;
	private String employee_certype;
	private String employee_cerstate;
	private FormFile employee_resume;
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getEmployee_sex() {
		return employee_sex;
	}
	public void setEmployee_sex(String employee_sex) {
		this.employee_sex = employee_sex;
	}
	public String getEmployee_address() {
		return employee_address;
	}
	public void setEmployee_address(String employee_address) {
		this.employee_address = employee_address;
	}
	public String getEmployee_email() {
		return employee_email;
	}
	public void setEmployee_email(String employee_email) {
		this.employee_email = employee_email;
	}
	public String getEmployee_phone() {
		return employee_phone;
	}
	public void setEmployee_phone(String employee_phone) {
		this.employee_phone = employee_phone;
	}
	public String getEmployee_certype() {
		return employee_certype;
	}
	public void setEmployee_certype(String employee_certype) {
		this.employee_certype = employee_certype;
	}
	
	public String getEmployee_cerstate() {
		return employee_cerstate;
	}
	public void setEmployee_cerstate(String employee_cerstate) {
		this.employee_cerstate = employee_cerstate;
	}
	public FormFile getEmployee_resume() {
		return employee_resume;
	}
	public void setEmployee_resume(FormFile employee_resume) {
		System.out.println(employee_resume+"");
		this.employee_resume = employee_resume;
	}

}
