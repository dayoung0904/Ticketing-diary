package com.ticketingdiary.diary;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticketingdiary.booking.bo.BookingBO;
import com.ticketingdiary.booking.domain.MyTicket;
import com.ticketingdiary.diary.bo.DiaryBO;
import com.ticketingdiary.diary.domain.Diary;

@RequestMapping("/diary")
@Controller
public class diaryController {

	@Autowired
	private BookingBO bookingBO;
	
	@Autowired
	private DiaryBO diaryBO;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 다이어리 view
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/diary-list-view")
	public String diaryListView(Model model,
			HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		
		if(userId == null) {
			logger.info("[다이어리 화면] 비로그인 상태");
			return "redirect:/user/sign-in-view";
		}
		
		model.addAttribute("viewName", "diary/diaryList");
		return "template/layout";
	}
	
	/**
	 * 다이어리 작성 view
	 * @param bookingId
	 * @param model
	 * @return
	 */
	@GetMapping("/create-view")
	public String diaryCreate(
			@RequestParam("bookingId") int bookingId,
			Model model) {
		
		Diary diary = diaryBO.findByBookingId(bookingId);
		MyTicket myTicket = bookingBO.findMyTicketBybookingId(bookingId);
		
		model.addAttribute("diary", diary);
		model.addAttribute("myTicket", myTicket);
		model.addAttribute("viewName", "diary/create");
		return "template/layout";
	}
}
