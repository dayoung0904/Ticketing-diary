package com.ticketingdiary.booking.domain;

import com.ticketingdiary.booking.Entiry.BookingEntity;
import com.ticketingdiary.show.domain.Show;

import lombok.Data;

@Data
public class MyTicket {

	// booking 1개
	private BookingEntity bookingEntity;
	
	// show 1개
	private Show show;
}
