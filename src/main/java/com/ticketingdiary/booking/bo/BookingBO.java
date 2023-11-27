package com.ticketingdiary.booking.bo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketingdiary.booking.Entiry.BookingEntity;
import com.ticketingdiary.booking.repository.BookingRepository;

@Service
public class BookingBO {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Integer addBooking() {
		// save
		BookingEntity userEntity = bookingRepository.save(
				BookingEntity.builder()
				.loginId(loginId)
				.password(password)
				.name(name)
				.email(email)
				.build());
		return userEntity == null ? null : userEntity.getId();
	}
	
}
