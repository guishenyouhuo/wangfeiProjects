package com.hunter.huanqiu.entity;

import java.sql.Timestamp;

/**
 * Bill entity. @author MyEclipse Persistence Tools
 */

public class Bill implements java.io.Serializable {

	// Fields

	private String billId;
	private User user;
	private Contract contract;
	private String billName;
	private String billNewname;
	private Float billMoney;
	private Timestamp billTime;
	private String billAbstract;
	private Integer billZt;

	// Constructors

	/** default constructor */
	public Bill() {
	}

	/** minimal constructor */
	public Bill(String billId, User user, Contract contract, String billName,
			String billNewname, Float billMoney, Timestamp billTime,
			Integer billZt) {
		this.billId = billId;
		this.user = user;
		this.contract = contract;
		this.billName = billName;
		this.billNewname = billNewname;
		this.billMoney = billMoney;
		this.billTime = billTime;
		this.billZt = billZt;
	}

	/** full constructor */
	public Bill(String billId, User user, Contract contract, String billName,
			String billNewname, Float billMoney, Timestamp billTime,
			String billAbstract, Integer billZt) {
		this.billId = billId;
		this.user = user;
		this.contract = contract;
		this.billName = billName;
		this.billNewname = billNewname;
		this.billMoney = billMoney;
		this.billTime = billTime;
		this.billAbstract = billAbstract;
		this.billZt = billZt;
	}

	// Property accessors

	public String getBillId() {
		return this.billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getBillName() {
		return this.billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public String getBillNewname() {
		return this.billNewname;
	}

	public void setBillNewname(String billNewname) {
		this.billNewname = billNewname;
	}

	public Float getBillMoney() {
		return this.billMoney;
	}

	public void setBillMoney(Float billMoney) {
		this.billMoney = billMoney;
	}

	public Timestamp getBillTime() {
		return this.billTime;
	}

	public void setBillTime(Timestamp billTime) {
		this.billTime = billTime;
	}

	public String getBillAbstract() {
		return this.billAbstract;
	}

	public void setBillAbstract(String billAbstract) {
		this.billAbstract = billAbstract;
	}

	public Integer getBillZt() {
		return this.billZt;
	}

	public void setBillZt(Integer billZt) {
		this.billZt = billZt;
	}

}