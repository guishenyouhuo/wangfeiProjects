package com.hunter.huanqiu.form;

import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;

public class RecordForm extends ActionForm {
	private String record_theme;
	private String record_time;
	private String record_abstract;
	public String getRecord_theme() {
		return record_theme;
	}
	public void setRecord_theme(String record_theme) {
		this.record_theme = record_theme;
	}
	public String getRecord_time() {
		return record_time;
	}
	public void setRecord_time(String record_time) {
		this.record_time = record_time;
	}
	public String getRecord_abstract() {
		return record_abstract;
	}
	public void setRecord_abstract(String record_abstract) {
		this.record_abstract = record_abstract;
	}
}
