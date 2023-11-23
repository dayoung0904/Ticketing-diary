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
				<div class="my-2">공연장소 : ${show.location}</div>
				<div class="my-2">장르 : ${show.category}</div>
				<div class="my-2">단체명 : ${show.group}</div>
			</div>
		</div>
	</div>
</div>
<div class="d-flex justify-content-center my-5">
	<input type="date" id="bookingDate" name="bookingDate" 
	min="<fmt:formatDate value="${show.startDate}" pattern="yyyy-MM-dd" />" 
	max="<fmt:formatDate value="${show.endDate}" pattern="yyyy-MM-dd" />"
	value="<fmt:formatDate value="${show.startDate}" pattern="yyyy-MM-dd" />">
</div>

<script>

</script>