package com.ticketingdiary.booking;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class bookingRestController {

	@PostMapping("/create")
	public Map<String, Object> createBooking(
			@RequestParam("ticketCount") int buy,
			@RequestParam("bookingAmount") int amount,
			@RequestParam("agreement") String agreement,
			@RequestParam("showId") int showId,
			@RequestParam("bookingDate") String viewDate,
			HttpSession session){
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			result.put("code", 500); // 비로그인 상태
			result.put("result", "error");
			result.put("errorMessage", "로그인을 해주세요.");
			return result;
		}
		
		// bookingBO insert
		
		
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
}
