package com.yashun.bean;

public class UserBean {
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGs_name() {
		return gs_name;
	}
	public void setGs_name(String gs_name) {
		this.gs_name = gs_name;
	}
	public String getGs_num() {
		return gs_num;
	}
	public void setGs_num(String gs_num) {
		this.gs_num = gs_num;
	}
	public String getPws() {
		return pws;
	}
	public void setPws(String pws) {
		this.pws = pws;
	}
	public int getTy_flag() {
		return ty_flag;
	}
	public void setTy_flag(int ty_flag) {
		this.ty_flag = ty_flag;
	}
	private String gs_name;
	private String gs_num;
	private String pws;
	private int ty_flag;

}
