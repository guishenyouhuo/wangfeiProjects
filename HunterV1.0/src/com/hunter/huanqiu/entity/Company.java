package com.hunter.huanqiu.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Company entity. @author MyEclipse Persistence Tools
 */

public class Company implements java.io.Serializable {

	// Fields

	private String CId;
	private User user;
	private String CName;
	private String CLinkman;
	private String CSex;
	private String CTitle;
	private String CAddress;
	private String CPhone;
	private String CEmail;
	private Timestamp CEntrytime;
	private Integer CZt;
	private Set records = new HashSet(0);
	private Set contracts = new HashSet(0);
	//2014.06.19Ìí¼Ó
	private String CTelephone;
	private String CSecondlinkman;
	private String CSecondphone;
	private String CNeed;
	

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(String cId, User user, String cName, String cLinkman,
			String cSex, String cTitle, String cAddress, String cPhone,
			String cEmail, Timestamp cEntrytime, Integer cZt, Set records,
			Set contracts, String cSecondphone, String cNeed) {
		
		this.CId = cId;
		this.user = user;
		this.CName = cName;
		this.CLinkman = cLinkman;
		this.CSex = cSex;
		this.CTitle = cTitle;
		this.CAddress = cAddress;
		this.CPhone = cPhone;
		this.CEmail = cEmail;
		this.CEntrytime = cEntrytime;
		this.CZt = cZt;
		this.records = records;
		this.contracts = contracts;
		this.CSecondphone = cSecondphone;
		this.CNeed = cNeed;
	}


	/** full constructor */
	
	public Company(String cId, User user, String cName, String cLinkman,
			String cSex, String cTitle, String cAddress, String cPhone,
			String cEmail, Timestamp cEntrytime, Integer cZt, Set records,
			Set contracts, String cTelephone, String cSecondlinkman,
			String cSecondphone, String cNeed) {
		
		this.CId = cId;
		this.user = user;
		this.CName = cName;
		this.CLinkman = cLinkman;
		this.CSex = cSex;
		this.CTitle = cTitle;
		this.CAddress = cAddress;
		this.CPhone = cPhone;
		this.CEmail = cEmail;
		this.CEntrytime = cEntrytime;
		this.CZt = cZt;
		this.records = records;
		this.contracts = contracts;
		this.CTelephone = cTelephone;
		this.CSecondlinkman = cSecondlinkman;
		this.CSecondphone = cSecondphone;
		this.CNeed = cNeed;
	}


	

	// Property accessors

	public String getCId() {
		return this.CId;
	}

	public void setCId(String CId) {
		this.CId = CId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public String getCLinkman() {
		return this.CLinkman;
	}

	public void setCLinkman(String CLinkman) {
		this.CLinkman = CLinkman;
	}

	public String getCSex() {
		return this.CSex;
	}

	public void setCSex(String CSex) {
		this.CSex = CSex;
	}

	public String getCTitle() {
		return this.CTitle;
	}

	public void setCTitle(String CTitle) {
		this.CTitle = CTitle;
	}

	public String getCAddress() {
		return this.CAddress;
	}

	public void setCAddress(String CAddress) {
		this.CAddress = CAddress;
	}

	public String getCPhone() {
		return this.CPhone;
	}

	public void setCPhone(String CPhone) {
		this.CPhone = CPhone;
	}

	public String getCEmail() {
		return this.CEmail;
	}

	public void setCEmail(String CEmail) {
		this.CEmail = CEmail;
	}

	public Timestamp getCEntrytime() {
		return this.CEntrytime;
	}

	public void setCEntrytime(Timestamp CEntrytime) {
		this.CEntrytime = CEntrytime;
	}

	public Integer getCZt() {
		return this.CZt;
	}

	public void setCZt(Integer CZt) {
		this.CZt = CZt;
	}

	public Set getRecords() {
		return this.records;
	}

	public void setRecords(Set records) {
		this.records = records;
	}

	public Set getContracts() {
		return this.contracts;
	}

	public void setContracts(Set contracts) {
		this.contracts = contracts;
	}
	//2014.06.19Ìí¼Ó
	public String getCTelephone() {
		return CTelephone;
	}

	public void setCTelephone(String cTelephone) {
		CTelephone = cTelephone;
	}

	public String getCSecondlinkman() {
		return CSecondlinkman;
	}

	public void setCSecondlinkman(String cSecondlinkman) {
		CSecondlinkman = cSecondlinkman;
	}

	public String getCSecondphone() {
		return CSecondphone;
	}

	public void setCSecondphone(String cSecondphone) {
		CSecondphone = cSecondphone;
	}

	public String getCNeed() {
		return CNeed;
	}

	public void setCNeed(String cNeed) {
		CNeed = cNeed;
	}

}