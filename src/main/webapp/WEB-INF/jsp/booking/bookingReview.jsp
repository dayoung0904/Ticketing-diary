<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex">
	<div>
		<img src="#" alt="공연 포스터" >
	</div>
	<div>
		<div>title1</div>
		<div>관람일:20XX.X.XX</div>
		<div>공연기간:20XX.X.XX ~ 20XX.X.XX</div>
		<div>공연장소:예술의 전당 IBK챔버홀</div>
		<div>단체명:(주)스테이지원</div>
	</div>
</div>
<div>
	<div>공연은 어떠셨나요?</div>
	별점
</div>
<div>
	<div>리뷰를 남겨주세요.</div>
	<imput class="comment" type="text">
</div>
<div>
	<a class="btn btn-secondary" href="/booking/booking-list-view">목록으로</a>
	<button type="button" id="saveBtn" class="btn btn-secondary">저장하기</button>
</div>

<script>
$(document).ready(function(){
	// 글 저장
	$('#saveBtn').on('click', function(){
		//alert("저장버튼");
		let comment = 
	});
});
</script>