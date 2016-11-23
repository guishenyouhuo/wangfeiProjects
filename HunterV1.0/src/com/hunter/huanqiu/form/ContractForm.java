package com.hunter.huanqiu.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ContractForm extends ActionForm {
	String belongcid;
	String belongcompany;
	float contractmoney;
	String contractabstract;
	FormFile  contractname;
	public String getBelongcid() {
		return belongcid;
	}
	public void setBelongcid(String belongcid) {
		this.belongcid = belongcid;
	}
	public String getBelongcompany() {
		return belongcompany;
	}
	public void setBelongcompany(String belongcompany) {
		this.belongcompany = belongcompany;
	}
	public float getContractmoney() {
		return contractmoney;
	}
	public void setContractmoney(float contractmoney) {
		this.contractmoney = contractmoney;
	}
	public String getContractabstract() {
		return contractabstract;
	}
	public void setContractabstract(String contractabstract) {
		this.contractabstract = contractabstract;
	}
	public FormFile getContractname() {
		return contractname;
	}
	public void setContractname(FormFile contractname) {
		this.contractname = contractname;
	}

}
