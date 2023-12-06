package com.ticketingdiary;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class 람다테스트 {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Test
	void 람다테스트1() {
		List<String> list = List.of("apple", "banana", "grape");
		
		// stream
		list
		.stream()	// 계속 줄줄이 이어지다. stream 타입의 값을 돌려준다.
		.filter(e -> e.startsWith("b"))	// b를 찾아내고
		.forEach(e -> logger.info("###### {}", e));	// 진짜 반복문 돌아서 출력하기
	}
	
	//@Test
	void 람다테스트2() {
		List<String> list = List.of("apple", "banana", "grape");
		
		list = list
		.stream()
		.map(e -> e.toUpperCase())	// 대문자로 바꾸기 stream
		.collect(Collectors.toList());	// stream to list
		
		logger.info("#### {}", list);
	}
	
	@Test
	void 메소드레퍼런스() {
		List<String> list = List.of("apple", "banana", "grape");
		
		list = list
		.stream()
		.map(String::toUpperCase) 	// e -> e.toUpperCase()와 동일
		.collect(Collectors.toList());
		
		logger.info("### {}", list);
	}
}
