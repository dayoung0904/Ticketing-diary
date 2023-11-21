package com.ticketingdiary.user.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class kakaoLoginForm {

	private String name;
	private String email;
	private String phoneNumber;
	
	public kakaoLoginForm(String name, String email, String phoneNumber) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
}
