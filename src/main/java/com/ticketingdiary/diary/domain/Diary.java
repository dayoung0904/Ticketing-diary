package com.ticketingdiary.diary.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Diary {
	
	private int id;
	private int bookingId;
	private int userId;
	private String content;
	private Date createdAt;
	private Date updatedAt;

}
