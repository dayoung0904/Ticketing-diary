<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="h-100 d-flex">
	<div class="col-2">
	</div>
	<div class="col-8 d-flex flex-wrap">
		<div class="show-box">
			<div><img alt="공연포스터" src="#"></div>
			<a href="/show/show-detail-view?showId=''">title</a>
			<div><img class="star" alt="별점" src="/static/img/star_fill.png"><span>4.5점</span></div>
		</div>
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