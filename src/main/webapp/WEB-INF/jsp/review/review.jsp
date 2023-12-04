<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table>
	<tbody>
		<tr>
			<td><div class="my-3"><img class="poster" src="${show.posterImagePath}" alt="공연 포스터"></div></td>
			<td>
				<div class="my-3"><h3>${show.name}</h3></div>
				<div>관람일 : <fmt:formatDate value="${bookingEntity.viewDate}" pattern="yyyy년 M월 d일" /></div>
				<div>공연기간 : 
					<fmt:formatDate value="${show.startDate}" pattern="yyyy년 M월 d일" /> ~
					<fmt:formatDate value="${show.endDate}" pattern="yyyy년 M월 d일" />
				</div>
				<div>공연장소 : ${show.location}</div>
				<div>단체명 : ${show.group}</div>
			</td>
		</tr>
		<tr>
			<td><div class="my-3 mr-5"><h6>공연은 어떠셨나요?</h6></div></td>
			<td>
				<form class="mb-3" name="myform" id="myform" method="post">
					<fieldset>
						<span class="text-bold">별점을 선택해주세요</span>
						<input type="radio" name="reviewStar" value="5" id="rate1"><label
							for="rate1">★</label>
						<input type="radio" name="reviewStar" value="4" id="rate2"><label
							for="rate2">★</label>
						<input type="radio" name="reviewStar" value="3" id="rate3"><label
							for="rate3">★</label>
						<input type="radio" name="reviewStar" value="2" id="rate4"><label
							for="rate4">★</label>
						<input type="radio" name="reviewStar" value="1" id="rate5"><label
							for="rate5">★</label>
					</fieldset>
					<div>
						<textarea class="col-auto form-control" type="text" id="comment"
								  placeholder="리뷰를 남겨주세요.(200자 이내)" maxlength="200"></textarea>
					</div>
				</form>	
			</td>
		</tr>
	</tbody>
</table>
				
<div class="my-5">
	<div class="d-flex justify-content-center">
		<a class="btn btn-secondary mr-5" href="/booking/booking-list-view">목록으로</a>
		<button type="submit" id="saveBtn" class="btn btn-secondary ml-5" 
		data-booking-id="${bookingEntity.id}" data-show-id="${show.id}">저장하기</button>
	</div>
</div>

<script>
$(document).ready(function(){
	
	$('input[name=reviewStar]').on('change', function(){
		let star = $(this).val();
		
	});
		
	$('#saveBtn').on('click',function(e){
		e.preventDefault();
		let selected = document.querySelector('input[type=radio][name=reviewStar]:checked');
		let star = selected.value;
		let comment = $('#comment').val();
		let bookingId = $(this).data('booking-id');
		let showId = $(this).data('show-id');
		// alert(star);
		
		$.ajax({
			type: "post"
			, url: "/review/create"
			, data: {"bookingId":bookingId, "showId":showId, "star":star, "comment":comment}
		
			// response
			,success:function(data){
				if(data.code == 200){
					alert("리뷰등록이 완료 되었습니다.");
					location.href= "/booking/booking-list-view"
				} else if (data.code == 500){
					alert(data.errorMessage);
					location.href= "/booking/booking-list-view"
				}
			}
			, error:function(request, status, error){
				alert("리뷰작성에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	
	});
});
</script>