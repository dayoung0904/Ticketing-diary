package com.ticketingdiary.review;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketingdiary.booking.Entiry.BookingEntity;
import com.ticketingdiary.booking.bo.BookingBO;
import com.ticketingdiary.show.bo.ShowBO;
import com.ticketingdiary.show.domain.Show;

@RequestMapping("/booking")
@Controller
public class reviewController {

	@Autowired
	private BookingBO bookingBO;
	
	@Autowired
	private ShowBO showBO;
	
	@GetMapping("/booking-review-view")
	public String bookingReviewView(
			@RequestParam("bookingId") int bookingId,
			HttpSession session,
			Model model) {
		
		// 사용자 id 추출
		int userId = (int)session.getAttribute("userId");
		
		BookingEntity bookingEntity = bookingBO.findBookingEntityById(bookingId);
		Show show = showBO.getShowById(bookingEntity.getShowId());
		
		model.addAttribute("bookingEntity", bookingEntity);
		model.addAttribute("show", show);
		model.addAttribute("viewName", "review/review");
		return "template/layout";
		
		
	}
}