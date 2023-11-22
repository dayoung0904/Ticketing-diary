package com.ticketingdiary.booking;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/booking")
@Controller
public class bookingController {

	@GetMapping("/booking-list-view")
	public String showListView(Model model) {
		
		model.addAttribute("viewName", "booking/bookingList");
		return "template/layout";
	}
}
