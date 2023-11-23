package com.ticketingdiary.show;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketingdiary.show.bo.ShowBO;
import com.ticketingdiary.show.domain.Show;

@RequestMapping("/show")
@Controller
public class showController {
	
	@Autowired
	private ShowBO showBO;
	
	@GetMapping("/list-view")
	public String showListView(Model model) {
		
		List<Show> showList = showBO.getShowListLimit();
		
		model.addAttribute("showList", showList);
		model.addAttribute("viewName", "show/showList");
		return "template/layout";
	}
	
	@GetMapping("/list-detail-view")
	public String listDetailView(Model model) {
		model.addAttribute("viewName", "show/showDetailList");
		return "template/layout";
	}
	
	@GetMapping("/show-detail-view")
	public String showDetailView(
			Model model,
			@RequestParam("showId") int showId) {
		
		Show show = showBO.getShowById(showId);
		
		model.addAttribute("show", show);
		model.addAttribute("viewName", "show/showDetail");
		return "template/layout";
	}
}
