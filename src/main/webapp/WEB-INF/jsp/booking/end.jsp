<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2 class="text-info text-center">예매가 정상적으로 완료 되었습니다.</h2>
<div class="d-flex justify-content-center my-5">
	<div class="text-center">
		<div class="mb-3"><h3>${show.name}</h3></div>
		<div class="viewDate">관람일 : <fmt:formatDate value="${bookingEntity.viewDate}" pattern="yyyy년 M월 d일" /></div>
		<div class="buy">매수 : ${bookingEntity.buy}매</div>
	</div>
</div>    
<hr class="hrcss-booking"></hr>
<div class="d-flex justify-content-center my-5">
	<div class="text-center">
		<div>입금 정보 : XX은행 123-4567-8901</div>
		<div>입금 기한 : <fmt:formatDate value="${bookingEntity.createdAt}" pattern="yyyy년 M월 d일" /> 23시 59분까지</div>
		<div class="mt-4"><h2>총 결제 금액 : ${bookingEntity.amount}</h2></div>
	</div>
</div>
<div class="d-flex justify-content-center m-3">
	<a class="btn btn-secondary text-white mx-3" href="/show/list-view">홈으로</a>
	<a class="btn btn-secondary text-white mx-3" href="/booking/booking-list-view">예매 내역 확인</a>
</div>