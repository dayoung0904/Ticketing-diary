<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex justify-content-center">
	<table class="w-75">
		<thead class="text-center">
			<tr>
				<th>예매일</th>
				<th>공연정보</th>
				<th>예매정보</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${myTicketList}" var="myTicket">
			<tr style='height:180px;'>
				<td class="text-center"><h6><fmt:formatDate value="${myTicket.bookingEntity.createdAt}" pattern="yyyy.M.d" /></h6></td>
				<td>
					<div class="d-flex">
						<div class="ml-5">
							<img class="poster" src="${myTicket.show.posterImagePath}" alt="공연 포스터">
						</div>
						<div class="ml-5">
							<div class="mb-5"><a href="/booking/booking-review-view?bookingId=${myTicket.bookingEntity.id}">${myTicket.show.name}</a></div>
							<div class="mt-5">공연기간 : 
								<fmt:formatDate value="${myTicket.show.startDate}" pattern="yyyy년 M월 d일" /> ~
								<fmt:formatDate value="${myTicket.show.endDate}" pattern="yyyy년 M월 d일" /></div>
							<div>공연장소 : ${myTicket.show.location}</div>
						</div>
					</div>
				</td>
				<td>
					<div class="ml-5">
						<div>관람일:<fmt:formatDate value="${myTicket.bookingEntity.viewDate}" pattern="yyyy년 M월 d일"></fmt:formatDate></div>
						<div class="mb-3">매수:${myTicket.bookingEntity.buy}</div>
					</div>
				</td>
				<td class="text-center"><b>${myTicket.bookingEntity.status}</b></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
