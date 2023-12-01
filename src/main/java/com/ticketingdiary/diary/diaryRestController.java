package com.ticketingdiary.diary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketingdiary.booking.bo.BookingBO;
import com.ticketingdiary.booking.domain.MyTicket;

@RestController
public class diaryRestController {

	@Autowired
	private BookingBO bookingBO;
	
	@GetMapping("/diary-event")
	public List<Map<String, Object>> diaryEvent(HttpSession session){
		
		List<Map<String, Object>> diaryEventList = new ArrayList<Map<String, Object>>();
		
		// session userId get
		int userId = (Integer)session.getAttribute("userId");
		
		List<MyTicket> bookingList = bookingBO.generateMyTicketList(userId);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		for(MyTicket MyTicket : bookingList) {
			Map<String, Object> event = new HashMap<String, Object>();
			event.put("id", MyTicket.getBookingEntity().getId());
			event.put("title", MyTicket.getShow().getName());
			event.put("start", formatter.format(MyTicket.getBookingEntity().getViewDate()));
			diaryEventList.add(event);
		}
		return diaryEventList;
	}
}
