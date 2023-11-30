<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="pt-3 w-100 d-flex align-items-center justify-content-center">
	<ul class="nav nav-fill col-8">
		<li class="nav-item"><a href="/show/list-view" class="nav-link font-weight-bold">홈</a></li>
		<li class="nav-item"><a href="#" data-category="연극" class="nav-link category font-weight-bold">연극</a></li>
		<li class="nav-item"><a href="#" data-category="클래식" class="nav-link category font-weight-bold">클래식</a></li>
		<li class="nav-item"><a href="#" data-category="뮤지컬" class="nav-link category font-weight-bold">뮤지컬</a></li>
		<li class="nav-item"><a href="#" data-category="콘서트" class="nav-link category font-weight-bold">콘서트</a></li>
		<li class="nav-item"><a href="/booking/booking-list-view" class="nav-link font-weight-bold">나의 티켓</a></li>
	</ul>
</div>
<hr class="hrcss"></hr>

<script>
$(document).ready(function(){
	$('.category').on('click', function(e){
		e.preventDefault();
		let category = $(this).data('category');
		//alert(category);
		
		$.ajax({
			url: "/show/list-detail-view"
			,data: {"category":category}
		
			// response
			,success:function(data){
				$('.contents').html(data); // 이전 내용을 지우고 일부 부분만 변경된 내용을 넣는다.
			}
		});
	});
});
</script>