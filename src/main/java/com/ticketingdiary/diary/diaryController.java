package com.ticketingdiary.diary;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/diary")
@Controller
public class diaryController {

	@GetMapping("/diary-list-view")
	public String diaryListView(Model model,
			HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId == null) {
			return "redirect:/user/sign-in-view";
		}
		
		model.addAttribute("viewName", "diary/diaryList");
		return "template/layout";
	}
}
