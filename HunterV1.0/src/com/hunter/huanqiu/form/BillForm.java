package com.hunter.huanqiu.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class BillForm extends ActionForm {
	private String belongcontract;
	private float shouldpay;
	private float paynow;
	private String billabstract;
	FormFile billfile;
	public String getBelongcontract() {
		return belongcontract;
	}
	public void setBelongcontract(String belongcontract) {
		this.belongcontract = belongcontract;
	}
	public float getShouldpay() {
		return shouldpay;
	}
	public void setShouldpay(float shouldpay) {
		this.shouldpay = shouldpay;
	}
	public float getPaynow() {
		return paynow;
	}
	public void setPaynow(float paynow) {
		this.paynow = paynow;
	}
	public String getBillabstract() {
		return billabstract;
	}
	public void setBillabstract(String billabstract) {
		this.billabstract = billabstract;
	}
	public FormFile getBillfile() {
		return billfile;
	}
	public void setBillfile(FormFile billfile) {
		this.billfile = billfile;
	}

}
