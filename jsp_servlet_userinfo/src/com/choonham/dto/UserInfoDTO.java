package com.choonham.dto;

public class UserInfoDTO {
	
	private String name = null;
	private String id = null;
	private String pwd = null;
	
	public UserInfoDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void test() {
		System.out.print(this.name + " " + this.id + " " + this.pwd);
	}
}
