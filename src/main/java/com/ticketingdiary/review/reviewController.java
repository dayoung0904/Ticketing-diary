package com.ticketingdiary.review;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/booking")
@Controller
public class reviewController {

	@GetMapping("/booking-review-view")
	public String bookingReviewView(
			@RequestParam("bookingId") int bookingId,
			HttpSession session,
			Model model) {
		
		// 사용자 id 추출
		int userId = (int)session.getAttribute("userId");
		
		// booking id와 현재 사용자 id로 가져오기
		// Booking booking = bookingBO.getBookingByBookingUserId(bookingId, userId);
		
		// model.addAttribute("booking", booking);
		model.addAttribute("viewName", "booking/bookingReview");
		return "template/layout";
		
		
	}
}
