package com.yashun.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FileForm extends ActionForm {

	private FormFile excel;
	private String select;
	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public FormFile getExcel() {
		return excel;
	}
	
	public void setExcel(FormFile excel) {
		this.excel = excel;
	}
}
