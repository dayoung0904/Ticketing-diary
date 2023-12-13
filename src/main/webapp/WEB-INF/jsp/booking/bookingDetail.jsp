<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<div class="d-flex justify-content-center my-4">
	<div class="text-center">
		<div class="mb-3"><h3>${show.name}</h3></div>
		<div class="bookingDate" data-booking-date="${bookingDate}">선택 날짜 : ${bookingDate}</div>
	</div>
</div>    
<hr class="hrcss-booking"></hr>
<div class="d-flex justify-content-center my-5">
	<div class="mx-3">${show.ticketType}</div>
	<select name="ticketCount" id="ticketCount">
		<c:if test="${!empty ticketLimit}">
			<option>매수를 선택하세요.</option>
			<c:forEach items="${ticketLimit}" var="limit">
		  	<option value="${limit}">${limit}매</option>
			</c:forEach>
		</c:if>
		<c:if test="${empty ticketLimit}">
			<option>1인 제한 수량이 초과되었습니다.</option>
		</c:if>
	</select>
</div>
<div class="d-flex justify-content-center my-5">
	<div><h3>총 결제금액 : <span id="bookingAmount" data-show-price="${show.price}"></span></h3></div>
</div>
<div class="d-flex justify-content-center m-3">
	<button type="submit" id="bookingBtn" class="btn btn-primary">예매하기</button>
</div>


<!-- Modal 창-->
<div class="modal black-bg" id="modal">
	<%-- modal-dialog-centered:수직기준 가운데 정렬 --%>
	<div class="modal-dialog modal-dialog-centered modal-lg">
		<div class="modal-content text-center">
			<div class="container">
				<div>
				<b>[필수] 예매 및 취소 수수료/ 취소기한을 확인하였으며 동의합니다.</b><br><br>
					<table>
						<thead>
							<tr>
								<th>취소일</th>
								<th>취소수수료</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>예매일 ~ 공연 28일전</td>
								<td>없음</td>
							</tr>
							<tr>
								<td>공연 27일전 ~ 공연 21일전</td>
								<td>티켓금액의 10%</td>
							</tr>
							<tr>
								<td>공연 20일전 ~ 공연 14일전</td>
								<td>티켓금액의 20%</td>
							</tr>
							<tr>
								<td>공연 13일전 ~ 공연 7일전</td>
								<td>티켓금액의 30%</td>
							</tr>
							<tr>
								<td>공연 6일전 ~ 공연 1일전</td>
								<td>티켓금액의 40%</td>
							</tr>
						</tbody>
					</table><br>
				<b>- 예매 당일 취소할 경우에는 예매수수료 및 취소수수료는 환불가능 합니다. <br>(취소수수료는 취소 기한 내에 한함)</b>
				</div>
			</div>
			<div class="py-3 border-bottom">
				<a href="#" id="closeModal">닫기</a>			
			</div>
			<div class="py-3">
				<a href="#" id="agreementLink" data-agreement="동의" data-show-id="${show.id}">동의하기</a>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	
	$('#ticketCount').on('change',function(){
		//alert('zmfflr');
		let ticketCount = $('#ticketCount').val();
		let price = $('#bookingAmount').data('show-price');
		//alert(bookingAmount);
		
		$("#bookingAmount").text(ticketCount * price); 
	});
	
	$('#bookingBtn').on('click',function(){
		// 합계 금액 존재 여부
		if($("#bookingAmount").text() == null || $("#bookingAmount").text() == ""){
			alert("매수를 선택해주세요.");
			return;
		} 
		$('.black-bg').addClass('show-modal');
	});
	
	$('#modal #closeModal').on('click', function(){
	    $('.black-bg').removeClass('show-modal');
	  });
	
	$('#modal #agreementLink').on('click',function(){
		let ticketCount = $('#ticketCount').val();
		let price = $('#bookingAmount').data('show-price');
		let bookingAmount = ticketCount * price;
		let agreement = $('#agreementLink').data('agreement');
		let showId = $('#agreementLink').data('show-id');
		let bookingDate = $('.bookingDate').data('booking-date');
		//alert(bookingDate);
		
		$.ajax({
			type: "post"
			, url: "/booking/create"
			, data: {"ticketCount":ticketCount, "bookingAmount":bookingAmount, "agreement":agreement, 
				"showId":showId, "bookingDate":bookingDate}
		
			// response
			,success:function(data){
				if(data.code == 200){
					location.href= "/booking/end-view?bookingId=" + data.bookingId
				} else if (data.code == 500){
					alert(data.errorMessage);
				}
			}
			, error:function(request, status, error){
				alert("예매에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
	
});

</script>