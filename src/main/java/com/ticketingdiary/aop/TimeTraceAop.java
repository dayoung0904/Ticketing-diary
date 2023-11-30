package com.ticketingdiary.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect // 부가 기능 정의(advice) + 어디에 적용하는지?(pointcut) => 합친것
@Component
public class TimeTraceAop { // Singleton
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//@Around("execution(* com.ticketingdiary..*(..))") // 1. 패키지 범위 => 어디에 적용?(pointcut)
	@Around("@annotation(TimeTrace)") // 2. 어느 어노테이션이 붙어있을 때 사용할 것인가? 내가 만든것도, 기존에 있는 것도 가능
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		// 타겟이 적용하는 메소드 - joinPoint (어느 메소드에 껴넣을 것인가?)
		
		// 시간 측정
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object proceedObj = joinPoint.proceed(); // 본래 할 일 수행(ex: 회원가입)
		
		stopWatch.stop();
		logger.info("### 실행 시간(ms): " + stopWatch.getTotalTimeMillis());
		logger.info(stopWatch.prettyPrint());
		
		return proceedObj;
	}
}
