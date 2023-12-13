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
	
	/**
	 * 리뷰 view
	 * @param bookingId
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/booking-review-view")
	public String bookingReviewView(
			@RequestParam("bookingId") int bookingId,
			HttpSession session,
			Model model) {
		
		BookingEntity bookingEntity = bookingBO.findBookingEntityById(bookingId);
		Show show = showBO.getShowById(bookingEntity.getShowId());
		
		model.addAttribute("bookingEntity", bookingEntity);
		model.addAttribute("show", show);
		model.addAttribute("viewName", "review/review");
		return "template/layout";
		
		
	}
}
