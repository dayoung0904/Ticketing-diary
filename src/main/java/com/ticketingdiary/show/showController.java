package com.ticketingdiary.show;

import java.util.List;

import javax.servlet.http.HttpSession;

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
			@RequestParam(value = "prevId", required = false) Integer prevIdParam,
			@RequestParam(value = "nextId", required = false) Integer nextIdParam,
			Model model) {
		
		List<ShowStar> showStarList = showBO.generateShowStarList(prevIdParam, nextIdParam);
		
		int nextId = 0;
		int prevId = 0;
		if(showStarList.isEmpty() == false){
			// postList가 비어있을 때 오류를 방지하기 위함 []
			nextId = showStarList.get(showStarList.size() - 1).getShow().getId(); // 가져온 리스트의 가장 끝값(가장 작은 id)
			prevId = showStarList.get(0).getShow().getId();
			
			// 이전 방향의 끝인가?
			// prevId와 post 테이블의 가장 큰 id값과 같다면 이전 페이지 X
			if(showBO.isPrevLastPage(prevId)) {
				prevId = 0;
			}
			
			// 다음 방향의 끝인가?
			// nextId와 post 테이블의 가장 작은 id값과 같다면 다음 페이지 X
			if(showBO.istNextLastPage(nextId)) {
				nextId = 0;
			}
		}
		
		model.addAttribute("nextId", nextId);
		model.addAttribute("prevId", prevId);
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
