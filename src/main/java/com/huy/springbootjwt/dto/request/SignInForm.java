package com.huy.springbootjwt.dto.request;

public class SignInForm {
	private String usename;
	private String password;
	
	public SignInForm() {
		// TODO Auto-generated constructor stub
	}
	
	public SignInForm(String usename, String password) {
		super();
		this.usename = usename;
		this.password = password;
	}

	public String getUsename() {
		return usename;
	}

	public void setUsename(String usename) {
		this.usename = usename;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
