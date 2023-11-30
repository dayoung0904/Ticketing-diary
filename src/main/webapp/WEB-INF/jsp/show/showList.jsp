<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex align-items-center justify-content-center">
	<div class="d-flex flex-wrap">
		<c:forEach items="${showStarList}" var="showStar">
		<div class="mx-3 text-center">
			<a href="/show/show-detail-view?showId=${showStar.show.id}">
				<img class="poster" src="${showStar.show.posterImagePath}" alt="공연 포스터">
				<div>${showStar.show.name}</div>
			</a>
			<div>★${showStar.averageStar}</div>
		</div>
		</c:forEach>
	</div>
</div>
<div class="text-center">
	<a href="#">이전</a> <a href="#">다음</a>
</div>