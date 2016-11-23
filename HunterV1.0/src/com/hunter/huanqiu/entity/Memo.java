package com.hunter.huanqiu.entity;

import java.sql.Timestamp;

/**
 * Memo entity. @author MyEclipse Persistence Tools
 */

public class Memo implements java.io.Serializable {

	// Fields

	private String meId;
	private User user;
	private String memoTheme;
	private Timestamp memoTime;
	private String memoAbstract;
	private Integer memoZt;

	// Constructors

	/** default constructor */
	public Memo() {
	}

	/** minimal constructor */
	public Memo(String meId) {
		this.meId = meId;
	}

	/** full constructor */
	public Memo(String meId, User user, String memoTheme, Timestamp memoTime,
			String memoAbstract, Integer memoZt) {
		this.meId = meId;
		this.user = user;
		this.memoTheme = memoTheme;
		this.memoTime = memoTime;
		this.memoAbstract = memoAbstract;
		this.memoZt = memoZt;
	}

	// Property accessors

	public String getMeId() {
		return this.meId;
	}

	public void setMeId(String meId) {
		this.meId = meId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMemoTheme() {
		return this.memoTheme;
	}

	public void setMemoTheme(String memoTheme) {
		this.memoTheme = memoTheme;
	}

	public Timestamp getMemoTime() {
		return this.memoTime;
	}

	public void setMemoTime(Timestamp memoTime) {
		this.memoTime = memoTime;
	}

	public String getMemoAbstract() {
		return this.memoAbstract;
	}

	public void setMemoAbstract(String memoAbstract) {
		this.memoAbstract = memoAbstract;
	}

	public Integer getMemoZt() {
		return this.memoZt;
	}

	public void setMemoZt(Integer memoZt) {
		this.memoZt = memoZt;
	}

}