package com.hunter.huanqiu.form;

import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;

import com.hunter.huanqiu.entity.Employee;
import com.hunter.huanqiu.entity.User;

public class MemoForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -56505052505740511L;
	private String meId;
	private User user;
	private String memoTheme;
	private Timestamp memoTime;
	private String memoAbstract;
	private Integer memoZt;
	public String getMeId() {
		return meId;
	}
	public void setMeId(String meId) {
		this.meId = meId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMemoTheme() {
		return memoTheme;
	}
	public void setMemoTheme(String memoTheme) {
		this.memoTheme = memoTheme;
	}
	public Timestamp getMemoTime() {
		return memoTime;
	}
	public void setMemoTime(Timestamp memoTime) {
		this.memoTime = memoTime;
	}
	public String getMemoAbstract() {
		return memoAbstract;
	}
	public void setMemoAbstract(String memoAbstract) {
		this.memoAbstract = memoAbstract;
	}
	public Integer getMemoZt() {
		return memoZt;
	}
	public void setMemoZt(Integer memoZt) {
		this.memoZt = memoZt;
	}
}
