package com.ticketingdiary.show;

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
import com.ticketingdiary.show.domain.ShowStar;

@RequestMapping("/show")
@Controller
public class showController {
	
	@Autowired
	private ShowBO showBO;
	
	@GetMapping("/list-view")
	public String showListView(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			Model model) {
		
		if(page == null) {
			page = 0;
			pageSize = 5;
		}
		
		List<ShowStar> showStarList = showBO.findshowPaging(page, pageSize);
		
		
		model.addAttribute("showStarList", showStarList);
		model.addAttribute("viewName", "show/showList");
		return "template/layout";
	}
	
	@GetMapping("/list-detail-view")
	public String listDetailView(Model model,
			@RequestParam("category") String category) {
		
		List<ShowStar> showStarListBycategory = showBO.generateShowStarByCategory(category);
				
		model.addAttribute("showStarListBycategory", showStarListBycategory);
		return "show/showDetailList";
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
	
	@GetMapping("/show-map")
	public String showMap(Model model,
			@RequestParam("showId") int showId) {
		
		Show show = showBO.getShowById(showId);
		
		model.addAttribute("show", show);
		model.addAttribute("viewName", "show/showMap");
		return "template/layout";
	}
	
	
	
	
}
