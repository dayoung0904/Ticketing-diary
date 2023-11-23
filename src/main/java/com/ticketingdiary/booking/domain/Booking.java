package com.ticketingdiary.booking.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Booking {

	private int id;
	private int userId;
	private int showId;
	private Date viewDate;
	private int buy;
	private int amount;
	private String status;
	private String agreement;
	private Date createdAt;
	private Date updatedAt;
}
