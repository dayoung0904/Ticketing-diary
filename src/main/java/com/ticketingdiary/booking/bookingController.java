package com.ticketingdiary.booking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketingdiary.show.bo.ShowBO;
import com.ticketingdiary.show.domain.Show;

@RequestMapping("/booking")
@Controller
public class bookingController {

	@Autowired
	private ShowBO showBO;
	
	@GetMapping("/booking-list-view")
	public String bookingListView(Model model) {
		
		model.addAttribute("viewName", "booking/bookingList");
		return "template/layout";
	}
	
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
	
	
}
