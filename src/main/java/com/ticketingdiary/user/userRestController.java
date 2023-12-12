package com.ticketingdiary.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticketingdiary.common.EncryptUtils;
import com.ticketingdiary.user.bo.KakaoBO;
import com.ticketingdiary.user.bo.UserBO;
import com.ticketingdiary.user.domain.User;

@RequestMapping("/user")
@RestController
public class userRestController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private KakaoBO kakaoBO;
	
	/**
	 * 중복확인 API
	 * @param loginId
	 * @return
	 */
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
	
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param PhoneNumber
	 * @param email
	 * @return
	 */
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
	
	/**
	 * 로그인 API
	 * @param loginId
	 * @param password
	 * @param request
	 * @return
	 */
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
	
	/**
	 * 카카오 로그인 API	
	 * @param code
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/kakao-login")
	public void KakaoLogin(
			@RequestParam("code") String code,
			HttpSession session,
			HttpServletResponse response) throws IOException {
		String accessToken = kakaoBO.getAccessToken(code);
		HashMap<String, Object> userInfo = kakaoBO.getUserInfo(accessToken);
		
		logger.info("$$$$$userInfo : " + userInfo);
			
		// 회원 정보 조회(이메일과 닉네임으로)
		User user = userBO.findUserByNameEmail(String.valueOf(userInfo.get("nickname")), String.valueOf(userInfo.get("email")));
		
		// 로그인 처리
		if(user != null) {
			// 사용자가 있는 경우
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			
		} else {
			// 사용자가 없는 경우 
			//해당 메일과 닉네임으로 저장 다시 조회해서 & session 등록
			userBO.addKakaoUser(String.valueOf(userInfo.get("nickname")), String.valueOf(userInfo.get("email")));
			
			User kakaoUser = userBO.findUserByNameEmail(String.valueOf(userInfo.get("nickname")), String.valueOf(userInfo.get("email")));
			session.setAttribute("userId", kakaoUser.getId());
			session.setAttribute("userName", kakaoUser.getName());
			
		}
		response.sendRedirect("/show/list-view");
	}
}
