package com.ticketingdiary.booking;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/booking")
@Controller
public class bookingController {

	@GetMapping("/booking-list-view")
	public String bookingListView(Model model) {
		
		model.addAttribute("viewName", "booking/bookingList");
		return "template/layout";
	}
	
	
}
