package com.hunter.huanqiu.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Contract entity. @author MyEclipse Persistence Tools
 */

public class Contract implements java.io.Serializable {

	// Fields

	private String conId;
	private Company company;
	private Float conMoney;
	private String conNewname;
	private String conName;
	private Timestamp conTime;
	private Integer conZt;
	private Set bills = new HashSet(0);

	// Constructors

	/** default constructor */
	public Contract() {
	}

	/** minimal constructor */
	public Contract(String conId, Company company, Float conMoney,
			String conNewname, String conName, Timestamp conTime, Integer conZt) {
		this.conId = conId;
		this.company = company;
		this.conMoney = conMoney;
		this.conNewname = conNewname;
		this.conName = conName;
		this.conTime = conTime;
		this.conZt = conZt;
	}

	/** full constructor */
	public Contract(String conId, Company company, Float conMoney,
			String conNewname, String conName, Timestamp conTime,
			Integer conZt, Set bills) {
		this.conId = conId;
		this.company = company;
		this.conMoney = conMoney;
		this.conNewname = conNewname;
		this.conName = conName;
		this.conTime = conTime;
		this.conZt = conZt;
		this.bills = bills;
	}

	// Property accessors

	public String getConId() {
		return this.conId;
	}

	public void setConId(String conId) {
		this.conId = conId;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Float getConMoney() {
		return this.conMoney;
	}

	public void setConMoney(Float conMoney) {
		this.conMoney = conMoney;
	}

	public String getConNewname() {
		return this.conNewname;
	}

	public void setConNewname(String conNewname) {
		this.conNewname = conNewname;
	}

	public String getConName() {
		return this.conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public Timestamp getConTime() {
		return this.conTime;
	}

	public void setConTime(Timestamp conTime) {
		this.conTime = conTime;
	}

	public Integer getConZt() {
		return this.conZt;
	}

	public void setConZt(Integer conZt) {
		this.conZt = conZt;
	}

	public Set getBills() {
		return this.bills;
	}

	public void setBills(Set bills) {
		this.bills = bills;
	}

}