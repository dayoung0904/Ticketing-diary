package com.ticketingdiary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import lombok.Getter;

@SpringBootTest // spring boot를 기동
class TicketingDiaryApplicationTests {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Getter
	public enum Status{
		// 열거형 정의
		Y(1, true), // 생성자 호출
		N(0, false); // 생성자 호출
		
		// enum 항목에 정의된 필드(정의에서 괄호를 만들게 되면 필드가 필요하다)
		private int value1; // 첫번째 숫자를 위한 필드
		private boolean value2; // 두번째 boolean을 위한 필드
		
		// 생성자: 필드에 값을 세팅하는 역할
		Status(int value1, boolean value2){
			this.value1 = value1;
			this.value2 = value2;
		}
	}
	
	@Test
	void enum_테스트1() {
		// given - 준비
		Status status = Status.Y;
		
		// when - 실행
		int value1 = status.getValue1();
		boolean value2 = status.isValue2(); // boolean의 경우는 is로 get(물어봐야한다.)
		
		// then - 검증
		assertEquals(status, Status.Y);
		assertEquals(value1, 1);
		assertEquals(value2, true);
		
	}
	
	//@Test
	void 검사_테스트() {
		//String str = null;
		String str = "";
		if(ObjectUtils.isEmpty(str)) { // null or ""
			logger.info("### str은 null 또는 비어있다 ###");
		}
		
		//List<Integer> list = null;
		List<Integer> list = new ArrayList<>();
		if(ObjectUtils.isEmpty(list)) { // null or []
			logger.info("$$$$ list은 null 또는 비어있다 $$$$");
		}
	}
	
	//@Test
	void contextLoads() {
	}
	
	//@Test
	void 더하기테스트() {
		logger.debug("##### 더하기 테스트 수행 #####");
		int a = 10;
		int b = 20;
		assertEquals(30, a + b); // 정답, 수행문
	}
	
}
