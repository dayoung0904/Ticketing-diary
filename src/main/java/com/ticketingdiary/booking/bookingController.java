package com.ticketingdiary.booking;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketingdiary.booking.Entiry.BookingEntity;
import com.ticketingdiary.booking.bo.BookingBO;
import com.ticketingdiary.booking.domain.MyTicket;
import com.ticketingdiary.show.bo.ShowBO;
import com.ticketingdiary.show.domain.Show;

@RequestMapping("/booking")
@Controller
public class bookingController {

	@Autowired
	private ShowBO showBO;
	
	@Autowired
	private BookingBO bookingBO;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 예약 리스트 view
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/booking-list-view")
	public String bookingListView(Model model,
			HttpSession session) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId == null) {
			logger.info("[예약리스트 view] 비로그인 상태");
			return "redirect:/user/sign-in-view";
		}
		
		List<MyTicket> myTicketList = bookingBO.generateMyTicketList(userId);
		
		model.addAttribute("myTicketList", myTicketList);
		model.addAttribute("viewName", "booking/bookingList");
		return "template/layout";
	}
	
	/**
	 * 예약하기 view
	 * @param model
	 * @param showId
	 * @param bookingDate
	 * @return
	 */
	@GetMapping("/booking-detail-view")
	public String bookingDetailView(Model model,
			@RequestParam("showId") int showId,
			@RequestParam("bookingDate") String bookingDate) {
		
		Show show = showBO.getShowById(showId);
		List ticketLimit = new ArrayList<>();
		for (int i = 0; i < show.getLimitBuy(); i++) {
            ticketLimit.add(i+1);
		}

		model.addAttribute("ticketLimit", ticketLimit);
		model.addAttribute("bookingDate", bookingDate);
		model.addAttribute("show", show);	
		model.addAttribute("viewName", "booking/bookingDetail");
		return "template/userLayout";
	}
	
	/**
	 * 예약완료 view
	 * @param model
	 * @param bookingId
	 * @return
	 */
	@GetMapping("/end-view")
	public String bookingEndView(Model model,
			@RequestParam("bookingId") int bookingId) {
		
		BookingEntity bookingEntity = bookingBO.findBookingEntityById(bookingId);
		Show show = showBO.getShowById(bookingEntity.getShowId());
		
		model.addAttribute("show", show);
		model.addAttribute("bookingEntity", bookingEntity);
		model.addAttribute("viewName", "booking/end");
		return "template/userLayout";
	}
}
