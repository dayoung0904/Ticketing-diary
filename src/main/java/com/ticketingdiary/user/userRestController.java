package com.ticketingdiary.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.manager.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketingdiary.common.EncryptUtils;
import com.ticketingdiary.user.bo.UserBO;
import com.ticketingdiary.user.domain.User;
import com.ticketingdiary.user.domain.kakaoLoginForm;

@RequestMapping("/user")
@RestController
public class userRestController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserBO userBO;
	
	@RequestMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedId(
			@RequestParam("loginId") String loginId){
		
		// db 조회
		User user = userBO.findUSerByLoginId(loginId);
		
		// response
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		
		if(user == null) { // 중복 아님
			result.put("isDuplicated", false);
		} else { // 중복
			result.put("isDuplicated", true);
		}
		return result;
	}
	
	
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String PhoneNumber,
			@RequestParam("email") String email){
		
		// 비밀번호 암호화
		String hashedPassword = EncryptUtils.md5(password);
		
		// db 저장
		Integer add = userBO.addUser(loginId, hashedPassword, name, PhoneNumber, email);
		
		// response
		Map<String, Object> result = new HashMap<>();
		if(add == null) {
			result.put("code", 500);
			result.put("errormessage", "회원가입 하는데 실패했습니다.");
		} else {
			result.put("code", 200);
			result.put("result", "성공");
		}
		return result;
	}
	
	
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request){
		
		// 비밀번호 해싱
		String hashedPassword = EncryptUtils.md5(password);
		
		// db 조회(id랑 비번으로)
		User user = userBO.findUserByLoginIdPassword(loginId, hashedPassword);
		
		// 결과 + session setting
		Map<String, Object> result = new HashMap<>();
		if(user != null) {
			// 로그인 처리
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userLoginId", user.getLoginId());
			
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			// 로그인 불가
			result.put("code", 500);
			result.put("errorMessage", "존재하지 않는 사용자 입니다.");
		}
		
		return result;
	}
	
	// kakao로그인 요청을 처리한다.
		@PostMapping("/kakao-login")
		public String loginWithKakao(kakaoLoginForm form,
				HttpServletRequest request){
			logger.info("카카오 로그인 인증정보:"+ form);
			
			HttpSession session = request.getSession();
			
			User user = null;
			user.setLoginId(form.getEmail());
			user.setPassword(null);
			user.setName(form.getName());
			user.setPhoneNumber(form.getPhoneNumber());
			user.setEmail(form.getEmail());
			user.setLoginType("kakao");
			
			// 이메일로 가입여부 확인
			User savedUser = userBO.loginWithKaKao(user);
			
			// 저장된 회원정보가 없으면 저장해서 돌아온 회원정보를 세션에 저장, 있으면 그대로 조회한 회원정보를 저장.
			session.setAttribute("userId", savedUser.getId());
			session.setAttribute("userName", savedUser.getName());
			session.setAttribute("userLoginId", savedUser.getLoginId());
			
			
			return "redirect:/show/list-view";
		}
}
