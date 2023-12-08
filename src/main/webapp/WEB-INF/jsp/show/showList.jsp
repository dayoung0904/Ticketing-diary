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
<div class="text-center">
	<c:if test="${prevId ne 0}">
		<a href="/show/list-view?prevId=${prevId}" class="mr-5">&lt;&lt; 이전</a>
	</c:if>
	<c:if test="${nextId ne 0}">
		<a href="/show/list-view?nextId=${nextId}">다음 &gt;&gt;</a>
	</c:if>
</div>