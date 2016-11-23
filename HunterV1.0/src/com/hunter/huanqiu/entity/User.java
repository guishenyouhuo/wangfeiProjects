package com.hunter.huanqiu.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private String username;
	private String password;
	private String usertype;
	private Integer zt;
	private Set employees = new HashSet(0);
	private Set companies = new HashSet(0);
	private Set bills = new HashSet(0);
	private Set employees_1 = new HashSet(0);
	private Set companies_1 = new HashSet(0);
	private Set bills_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, String usertype, Integer zt) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.zt = zt;
	}

	/** full constructor */
	public User(String username, String password, String usertype, Integer zt,
			 Set employees, Set companies,
			Set bills, Set employees_1, Set companies_1, Set bills_1) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.zt = zt;

		this.employees = employees;
		this.companies = companies;
		this.bills = bills;
		this.employees_1 = employees_1;
		this.companies_1 = companies_1;
		this.bills_1 = bills_1;
	}

	// Property accessors

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public Integer getZt() {
		return this.zt;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	public Set getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set employees) {
		this.employees = employees;
	}

	public Set getCompanies() {
		return this.companies;
	}

	public void setCompanies(Set companies) {
		this.companies = companies;
	}

	public Set getBills() {
		return this.bills;
	}

	public void setBills(Set bills) {
		this.bills = bills;
	}

	public Set getEmployees_1() {
		return this.employees_1;
	}

	public void setEmployees_1(Set employees_1) {
		this.employees_1 = employees_1;
	}

	public Set getCompanies_1() {
		return this.companies_1;
	}

	public void setCompanies_1(Set companies_1) {
		this.companies_1 = companies_1;
	}

	public Set getBills_1() {
		return this.bills_1;
	}

	public void setBills_1(Set bills_1) {
		this.bills_1 = bills_1;
	}

}