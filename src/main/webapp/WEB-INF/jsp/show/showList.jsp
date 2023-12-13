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

<%-- paging --%>
<div class="text-center my-3">
	<a class="mx-2" href="/show/list-view?page=1&pageSize=5">1</a>
	<a class="mx-2" href="/show/list-view?page=2&pageSize=5">2</a>
	<a class="mx-2" href="/show/list-view?page=3&pageSize=5">3</a>
	<a class="mx-2" href="/show/list-view?page=4&pageSize=5">4</a>
	<a class="mx-2" href="/show/list-view?page=5&pageSize=5">5</a>
</div>