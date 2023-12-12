package com.ticketingdiary.booking.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketingdiary.booking.Entiry.BookingEntity;
import com.ticketingdiary.booking.domain.MyTicket;
import com.ticketingdiary.booking.repository.BookingRepository;
import com.ticketingdiary.show.bo.ShowBO;
import com.ticketingdiary.show.domain.Show;

@Service
public class BookingBO {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ShowBO showBO;	

	public Integer addBooking(int userId, int showId, Date date, 
			int buy, int amount, String agreement) {
		String status = "예약완료";
		
		// save
		BookingEntity bookingEntity = bookingRepository.save(
				BookingEntity.builder()
				.userId(userId)
				.showId(showId)
				.viewDate(date)
				.buy(buy)
				.amount(amount)
				.agreement(agreement)
				.status(status)
				.build());
		return bookingEntity == null ? null : bookingEntity.getId();
	}
	
	
	public BookingEntity findBookingEntityById(Integer bookingId) {
		
		return bookingRepository.findById(bookingId).orElse(null);
	}

	public List<MyTicket> generateMyTicketList(int userId){
		List<MyTicket> myTicketList = new ArrayList<>();
		
		// 유저의 booking을 가져온다
		List<BookingEntity> bookingEntityList = bookingRepository.findByUserId(userId);
		for(BookingEntity booking : bookingEntityList) {
			MyTicket myTicket = new MyTicket();
			
			myTicket.setBookingEntity(booking);
			
			// show 정보
			Show show = showBO.getShowById(booking.getShowId());
			myTicket.setShow(show);
			
			// MyTicketList에 넣어주기
			myTicketList.add(myTicket);
		}
		return myTicketList;
	}
	
	public MyTicket findMyTicketBybookingId(int bookingId) {
		
		MyTicket myTicket = new MyTicket();
		
		BookingEntity booking = bookingRepository.findById(bookingId);
		
		myTicket.setBookingEntity(booking);
		myTicket.setShow(showBO.getShowById(booking.getShowId()));
		
		return myTicket;
	}
}
