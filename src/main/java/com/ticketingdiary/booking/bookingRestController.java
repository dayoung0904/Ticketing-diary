package com.ticketingdiary.booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketingdiary.booking.Entiry.BookingEntity;
import com.ticketingdiary.booking.bo.BookingBO;

@RestController
@RequestMapping("/booking")
public class bookingRestController {

	@Autowired
	private BookingBO bookingBO;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 예약하기 API
	 * @param buy
	 * @param amount
	 * @param agreement
	 * @param showId
	 * @param viewDate
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> createBooking(
			@RequestParam("ticketCount") int buy,
			@RequestParam("bookingAmount") int amount,
			@RequestParam("agreement") String agreement,
			@RequestParam("showId") int showId,
			@RequestParam("bookingDate") String viewDate,
			HttpSession session){
		
		Integer userId = (Integer) session.getAttribute("userId");
		Date date = new Date();
		try {
            String str = viewDate;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(str);
        } catch(ParseException e) {
            e.printStackTrace();
        }
				
		Map<String, Object> result = new HashMap<>();
		if (userId == null) {
			logger.info("[예약하기] 비로그인 상태");
			result.put("code", 500); // 비로그인 상태
			result.put("result", "error");
			result.put("errorMessage", "로그인을 해주세요.");
			return result;
		}
		
		// bookingBO insert
		Integer bookingId = bookingBO.addBooking(userId, showId, date, buy, amount, agreement);
		
		result.put("bookingId", bookingId);
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
}
