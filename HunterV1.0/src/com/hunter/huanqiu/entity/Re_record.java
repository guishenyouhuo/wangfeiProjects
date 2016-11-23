package com.hunter.huanqiu.entity;

import java.sql.Timestamp;

/**
 * Record entity. @author MyEclipse Persistence Tools
 */

public class Re_record implements java.io.Serializable {

	// Fields

	private String RId;
	private Employee employee;
	private String RTheme;
	private String RUsername;
	private Timestamp RTime;
	private String RAbstract;
	private Integer RZt;

	// Constructors

	/** default constructor */
	public Re_record() {
	}

	/** minimal constructor */
	public Re_record(String RId, Employee employee, Integer RZt) {
		this.RId = RId;
		this.employee = employee;
		this.RZt = RZt;
	}

	/** full constructor */
	public Re_record(String RId, Employee employee, String RTheme, String RUsername,
			Timestamp RTime, String RAbstract, Integer RZt) {
		this.RId = RId;
		this.employee = employee;
		this.RTheme = RTheme;
		this.RUsername = RUsername;
		this.RTime = RTime;
		this.RAbstract = RAbstract;
		this.RZt = RZt;
	}

	// Property accessors

	public String getRId() {
		return this.RId;
	}

	public void setRId(String RId) {
		this.RId = RId;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getRTheme() {
		return this.RTheme;
	}

	public void setRTheme(String RTheme) {
		this.RTheme = RTheme;
	}

	public String getRUsername() {
		return this.RUsername;
	}

	public void setRUsername(String RUsername) {
		this.RUsername = RUsername;
	}

	public Timestamp getRTime() {
		return this.RTime;
	}

	public void setRTime(Timestamp RTime) {
		this.RTime = RTime;
	}

	public String getRAbstract() {
		return this.RAbstract;
	}

	public void setRAbstract(String RAbstract) {
		this.RAbstract = RAbstract;
	}

	public Integer getRZt() {
		return this.RZt;
	}

	public void setRZt(Integer RZt) {
		this.RZt = RZt;
	}

}