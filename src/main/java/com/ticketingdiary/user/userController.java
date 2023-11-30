package com.ticketingdiary.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ticketingdiary.aop.TimeTrace;

@RequestMapping("user")
@Controller
public class userController {

	@TimeTrace
	@GetMapping("/sign-in-view")
	public String signInView(Model model) {
		
		model.addAttribute("viewName", "user/signIn");
		return "template/userLayout";
	}
	
	@GetMapping("/sign-up-view")
	public String signUpView(Model model) {
		
		model.addAttribute("viewName", "user/signUp");
		return "template/userLayout";
	}
	
	@RequestMapping("/sign-out")
	public String signOut(HttpSession session) {
		// 세션에 있는 내용을 비운다.
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		session.removeAttribute("userLoginId");
		
		// 로그인 화면으로 이동
		return "redirect:/user/sign-in-view";
	}
}
