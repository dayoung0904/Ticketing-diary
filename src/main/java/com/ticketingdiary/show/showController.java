package com.ticketingdiary.show;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/show")
@Controller
public class showController {
	
	@GetMapping("/list-view")
	public String showListView(Model model) {
		
		model.addAttribute("viewName", "show/showList");
		return "template/layout";
	}
	
	@GetMapping("/list-detail-view")
	public String showDetailView(Model model) {
		model.addAttribute("viewName", "show/showDetailList");
		return "template/layout";
	}
}
