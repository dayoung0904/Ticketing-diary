package com.ticketingdiary.user.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class User {
	private int id;
	private String loginId;
	private String password;
	private String name;
	private String phoneNumber;
	private String email;
	private Date createdAt;
	private Date updatedAt;
}
