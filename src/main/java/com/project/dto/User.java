package com.project.dto;

public class User {
	
	// 네이버나 카카오에 연동해서 로그인할 때는 필요가 없다. 다만, 회원가입이나 로그인을 할 떄 사용자가
    // 폼에 정보를 기입하는 상황이라면 dto로 정보를 받아줘야한다.
	
	private String id;
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + "]";
	}
	
	

}
