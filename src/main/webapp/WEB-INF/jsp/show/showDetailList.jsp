<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="h-100 d-flex">
	<div class="col-2">
	</div>
	<div class="col-8 d-flex flex-wrap">
		<c:forEach items="${showStarListBycategory}" var="showStar">
		<div class="show-box">
			<a href="/show/show-detail-view?showId=${showStar.show.id}">
				<img class="poster" src="${showStar.show.posterImagePath}" alt="공연 포스터">
				<div>${showStar.show.name}</div>
			</a>
			<div>★${showStar.averageStar}</div>
		</div>
		</c:forEach>
	</div>
	<div class="col-2">
		<div class="my-5">
			<select name="region" id="region">
			  <option value="seoul">서울</option>
			  <option value="busan">부산</option>
			</select>
		</div>
		<div class="my-5">
			<select name="ticketType" id="ticketType">
			  <option value="Ticket">일반티켓</option>
			  <option value="saleTicket">할인티켓</option>
			  <option value="freeTicket">무료티켓</option>
			</select>
		</div>
	</div>
</div>
<div class="text-center">
	<a href="#">이전</a> <a href="#">다음</a>
</div>