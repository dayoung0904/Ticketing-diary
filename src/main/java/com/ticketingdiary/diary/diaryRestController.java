package com.ticketingdiary.diary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketingdiary.booking.bo.BookingBO;
import com.ticketingdiary.booking.domain.MyTicket;
import com.ticketingdiary.diary.bo.DiaryBO;

@RestController
public class diaryRestController {

	@Autowired
	private BookingBO bookingBO;
	
	@Autowired
	private DiaryBO diaryBO;
	
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
	
	@PostMapping("/diary/create")
	public Map<String, Object> diaryCreate(
			@RequestParam("bookingId") int bookingId,
			@RequestParam("content") String content,
			HttpSession session){
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 500); // 비로그인 상태
			result.put("result", "error");
			result.put("errorMessage", "로그인을 해주세요.");
			return result;
		}
		
		// db 저장
		diaryBO.addDiary(bookingId, userId, content);
		
		result.put("code", 200);
		result.put("result", "성공");
		return result;
		
	}
	
	@PostMapping("/diary/update")
	public Map<String, Object> diaryUpdate(
			@RequestParam("bookingId") int bookingId,
			@RequestParam("content") String content,
			HttpSession session){
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 500); // 비로그인 상태
			result.put("result", "error");
			result.put("errorMessage", "로그인을 해주세요.");
			return result;
		}
		
		// db 저장
		diaryBO.updateDiaryByBookingIdUserId(bookingId, userId, content);
		
		result.put("code", 200);
		result.put("result", "성공");
		return result;
		
	}
}
