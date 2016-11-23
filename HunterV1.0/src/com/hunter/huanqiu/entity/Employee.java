package com.hunter.huanqiu.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee implements java.io.Serializable {

	// Fields

	private String EId;
	private User user;
	private String EName;
	private String ESex;
	private String EPhone;
	private String EAddress;
	private String EEmail;
	private String ECertype;
	private String ECerstate;
	private Timestamp EEntrydate;
	private Integer EZt;
	private Set resumes = new HashSet(0);//¼òÀú

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(String EId, User user, String EName, String ESex,
			String EPhone, String ECertype, Timestamp EEntrydate, Integer EZt) {
		this.EId = EId;
		this.user = user;
		this.EName = EName;
		this.ESex = ESex;
		this.EPhone = EPhone;
		this.ECertype = ECertype;
		this.EEntrydate = EEntrydate;
		this.EZt = EZt;
	}

	/** full constructor */
	public Employee(String EId, User user, String EName, String ESex,
			String EPhone, String EAddress, String EEmail, String ECertype,
			Timestamp EEntrydate, Integer EZt, Set resumes) {
		this.EId = EId;
		this.user = user;
		this.EName = EName;
		this.ESex = ESex;
		this.EPhone = EPhone;
		this.EAddress = EAddress;
		this.EEmail = EEmail;
		this.ECertype = ECertype;
		this.EEntrydate = EEntrydate;
		this.EZt = EZt;
		this.resumes = resumes;
	}

	// Property accessors

	public String getEId() {
		return this.EId;
	}

	public void setEId(String EId) {
		this.EId = EId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEName() {
		return this.EName;
	}

	public void setEName(String EName) {
		this.EName = EName;
	}

	public String getESex() {
		return this.ESex;
	}

	public void setESex(String ESex) {
		this.ESex = ESex;
	}

	public String getEPhone() {
		return this.EPhone;
	}

	public void setEPhone(String EPhone) {
		this.EPhone = EPhone;
	}

	public String getEAddress() {
		return this.EAddress;
	}

	public void setEAddress(String EAddress) {
		this.EAddress = EAddress;
	}

	public String getEEmail() {
		return this.EEmail;
	}

	public void setEEmail(String EEmail) {
		this.EEmail = EEmail;
	}

	public String getECertype() {
		return this.ECertype;
	}

	public void setECertype(String ECertype) {
		this.ECertype = ECertype;
	}

	public String getECerstate() {
		return this.ECerstate;
	}

	public void setECerstate(String ECerstate) {
		this.ECerstate = ECerstate;
	}
	
	public Timestamp getEEntrydate() {
		return this.EEntrydate;
	}

	public void setEEntrydate(Timestamp EEntrydate) {
		this.EEntrydate = EEntrydate;
	}

	public Integer getEZt() {
		return this.EZt;
	}

	public void setEZt(Integer EZt) {
		this.EZt = EZt;
	}

	public Set getResumes() {
		return this.resumes;
	}

	public void setResumes(Set resumes) {
		this.resumes = resumes;
	}

}