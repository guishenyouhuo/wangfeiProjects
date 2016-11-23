package com.hunter.huanqiu.entity;

import java.sql.Timestamp;

/**
 * Resume entity. @author MyEclipse Persistence Tools
 */

public class Resume implements java.io.Serializable {

	// Fields

	private String resId;
	private Employee employee;
	private String resName;
	private String resOriname;
	private Timestamp resUptime;
	private Integer resZt;

	// Constructors

	/** default constructor */
	public Resume() {
	}

	/** full constructor */
	public Resume(String resId, Employee employee, String resName,String resOriname,
			Timestamp resUptime, Integer resZt) {
		this.resId = resId;
		this.employee = employee;
		this.resName = resName;
		this.resOriname=resOriname;
		this.resUptime = resUptime;
		this.resZt = resZt;
	}

	// Property accessors

	public String getResId() {
		return this.resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getResName() {
		return this.resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public Timestamp getResUptime() {
		return this.resUptime;
	}

	public void setResUptime(Timestamp resUptime) {
		this.resUptime = resUptime;
	}

	public Integer getResZt() {
		return this.resZt;
	}

	public void setResZt(Integer resZt) {
		this.resZt = resZt;
	}

	public String getResOriname() {
		return resOriname;
	}

	public void setResOriname(String resOriname) {
		this.resOriname = resOriname;
	}

}