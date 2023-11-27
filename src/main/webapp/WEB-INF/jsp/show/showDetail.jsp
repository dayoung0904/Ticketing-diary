<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="d-flex justify-content-center">
	<div class="d-flex col-8">
		<div class="mx-5">
			<img class="detailPoster" alt="포스터" src="${show.posterImagePath}">
		</div>
		<div>
			<div class="mb-5">
				<h3>${show.name}</h3>
			</div>
			<div>
				<div class="my-2">공연기간 : 
					<fmt:formatDate value="${show.startDate}" pattern="yyyy년 M월 d일" /> ~
					<fmt:formatDate value="${show.endDate}" pattern="yyyy년 M월 d일" />
				</div>
				<div class="my-2"><a href="#">공연장소 : ${show.location}</a></div>
				<div class="my-2">장르 : ${show.category}</div>
				<div class="my-2">단체명 : ${show.group}</div>
			</div>
		</div>
	</div>
</div>
<div class="d-flex justify-content-end mr-5">
	<div class="mx-5 d-flex align-items-center">
		<input type="date" id="bookingDate" name="bookingDate" 
		min="<fmt:formatDate value="${show.startDate}" pattern="yyyy-MM-dd" />" 
		max="<fmt:formatDate value="${show.endDate}" pattern="yyyy-MM-dd" />">
	</div>
	<div class="mx-5">
		<button id="bookingBtn" class="btn btn-block btn-info text-white my-3" data-show-id="${show.id}" data-user-name="${userName}">예매하기</button>
	</div>
</div>

<script>
$(document).ready(function(){
	$('#bookingBtn').on('click', function(e){
		e.preventDefault();
		
		if($(this).data('user-name') == null || $(this).data('user-name') == ""){
			alert("로그인 후 예매할 수 있습니다.");
			location.href = "/user/sign-in-view";
		} else{
		
			let bookingDate = $('#bookingDate').val();
			//alert(date);
			let showId = $(this).data('show-id');
			//alert(showId);
	
			location.href="/booking/booking-detail-view?showId=" + showId + "&bookingDate=" + bookingDate;
		}
	});
	
});

</script>