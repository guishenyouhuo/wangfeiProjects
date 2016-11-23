package com.hunter.huanqiu.entity;

import java.sql.Timestamp;

/**
 * Record entity. @author MyEclipse Persistence Tools
 */

public class Record implements java.io.Serializable {

	// Fields

	private String RId;
	private Company company;
	private String RTheme;
	private String RUsername;
	private Timestamp RTime;
	private String RAbstract;
	private Integer RZt;

	// Constructors

	/** default constructor */
	public Record() {
	}

	/** minimal constructor */
	public Record(String RId, Company company, Integer RZt) {
		this.RId = RId;
		this.company = company;
		this.RZt = RZt;
	}

	/** full constructor */
	public Record(String RId, Company company, String RTheme, String RUsername,
			Timestamp RTime, String RAbstract, Integer RZt) {
		this.RId = RId;
		this.company = company;
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

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
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