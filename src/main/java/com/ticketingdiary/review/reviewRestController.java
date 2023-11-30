package com.ticketingdiary.review;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketingdiary.review.bo.ReviewBO;
import com.ticketingdiary.review.domain.Review;

@RequestMapping("/review")
@RestController
public class reviewRestController {
	@Autowired
	private ReviewBO reviewBO;
	
	@PostMapping("/create")
	public Map<String, Object> reviewCreate(
			HttpSession session,
			@RequestParam("bookingId") int bookingId,
			@RequestParam("showId") int showId,
			@RequestParam("star") int star,
			@RequestParam("comment") String comment){
		Map<String, Object> result = new HashMap<>();
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		Review review = reviewBO.findReviewByUserIdBookingId(userId, bookingId);
		
		if (review != null) {
			result.put("code", 500);
			result.put("errorMessage", "기존에 등록된 리뷰가 있습니다.");
			return result;
		}
		
		// db 저장
		reviewBO.addReview(bookingId, userId, showId, star, comment);
		
		result.put("code", 200);
		result.put("result", "성공");
		
		return result;
	}
}
