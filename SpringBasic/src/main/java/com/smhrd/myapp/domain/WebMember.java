package com.smhrd.myapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebMember {
	
	private String email;
	private String pw;
	private String tel;
	private String address;
	
	// 이런식으로 몇개만 사용하는 생성자는 써준다 no lombok
	public WebMember(String email, String pw) {
		this.email = email;
		this.pw = pw;
	}
	
	// Lombok : 생성자 getter, setter 안써도 됨
	
	/*
	public WebMember(String email, String pw, String tel, String address) {
		this.email = email;
		this.pw = pw;
		this.tel = tel;
		this.address = address;
	}
	
	public WebMember(String email, String pw) {
		this.email = email;
		this.pw = pw;
	}
	
	// 기본생성자
	public WebMember() {
		
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public String getPw() {
		return pw;
	}

	public String getTel() {
		return tel;
	}

	public String getAddress() {
		return address;
	}
	
	*/


}
