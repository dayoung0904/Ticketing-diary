<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<div class="d-flex justify-content-center my-4">
	<div class="text-center">
		<div class="mb-3"><h3>${show.name}</h3></div>
		<div>선택 날짜 : ${bookingDate}</div>
	</div>
</div>    
<hr class="hrcss-booking"></hr>
<div class="d-flex justify-content-center my-5">
	<div class="mx-3">${show.ticketType}</div>
	<select name="ticketCount" id="ticketCount">
		<c:forEach items="${ticketLimit}" var="limit">
	  	<option value="${limit})">${limit}매</option>
		</c:forEach>
	</select>
</div>
<div class="d-flex justify-content-center my-5">
	<div><h3>총 결제금액</h3></div>
</div>