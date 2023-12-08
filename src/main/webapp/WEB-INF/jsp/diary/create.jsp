<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div class="d-flex justify-content-center">
	<div class="d-flex">
		<div>
			<img class="poster" src="${myTicket.show.posterImagePath}"
				alt="공연 포스터">
		</div>
		<div class="ml-3">
			<div class="mb-5">
				${myTicket.show.name}
			</div>
			<div class="mt-5">
				공연기간 :
				<fmt:formatDate value="${myTicket.show.startDate}" pattern="yyyy년 M월 d일" />
				~
				<fmt:formatDate value="${myTicket.show.endDate}" pattern="yyyy년 M월 d일" />
			</div>
			<div>공연장소 : ${myTicket.show.location}</div>
		</div>
	</div>
	<div class="ml-5">
		<div>
			관람일:
			<fmt:formatDate value="${myTicket.bookingEntity.viewDate}" pattern="yyyy년 M월 d일"></fmt:formatDate>
		</div>
		<div class="mb-3">매수:${myTicket.bookingEntity.buy}</div>
	</div>
</div>
<div class="container my-5 diary">
	<c:if test="${empty diary}">
	<textarea class="col-auto form-control" type="text" id="diaryCreate" placeholder="내용를 남겨주세요.(500자 이내)" maxlength="500"></textarea>
	</c:if>
	<c:if test="${not empty diary}">
	<textarea class="col-auto form-control" type="text" id="diaryUpdate"" maxlength="500">${diary.content}</textarea>
	</c:if>	
	
</div>
<div class="my-5">
	<div class="d-flex justify-content-center">
		<c:if test="${not empty diary}">
		<button id="upDateDiaryBtn" class="btn btn-info ml-5"
		data-booking-id='${myTicket.bookingEntity.id}'>수정하기</button>
		</c:if>	
		<c:if test="${empty diary}">
		<button id="saveDiaryBtn" class="btn btn-info ml-5"
		data-booking-id='${myTicket.bookingEntity.id}'>저장하기</button>
		</c:if>
	</div>
</div>

<script>
$(document).ready(function(){
	
	$('#saveDiaryBtn').on('click', function(){
		//alert("클릭");
		
		let bookingId = $(this).data("booking-id");
		let content = $('#diaryCreate').val();
		
		if(!content){
			alert("내용을 작성해주세요.");
			return false;
		}
		
		$.ajax({
			type: "post"
			, url: "/diary/create"
			, data: {"bookingId":bookingId, "content":content}
		
			// response
			,success:function(data){
				if(data.code == 200){
					alert("작성을 성공하였습니다.");
					location.href= "/diary/diary-list-view"
				} else if (data.code == 500){
					alert(data.errorMessage);
				}
			}
			, error:function(request, status, error){
				alert("작성에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
	
	
	$('#upDateDiaryBtn').on('click', function(){
		//alert("클릭");
		
		let bookingId = $(this).data("booking-id");
		let content = $('#diaryUpdate').val();
		
		if(!content){
			alert("내용을 작성해주세요.");
			return false;
		}
		
		$.ajax({
			type: "post"
			, url: "/diary/update"
			, data: {"bookingId":bookingId, "content":content}
		
			// response
			,success:function(data){
				if(data.code == 200){
					alert("수정을 성공하였습니다.");
					location.href= "/diary/diary-list-view"
				} else if (data.code == 500){
					alert(data.errorMessage);
				}
			}
			, error:function(request, status, error){
				alert("작성에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
});

</script>