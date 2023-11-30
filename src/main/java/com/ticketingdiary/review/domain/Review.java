package com.ticketingdiary.review.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Review {

	private int id;
	private int bookingId;
	private int userId;
	private int showId;
	private int star;
	private String comment;
	private Date createdAt;
	private Date updatedAt;
}
