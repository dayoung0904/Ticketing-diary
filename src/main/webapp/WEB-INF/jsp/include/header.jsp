<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="h-100 d-flex align-items-center justify-content-between">
	<a href="#">
		<div id="logo" class="text-center mx-5 px-5">
			<h1>Ticket</h1>
			<h2>diary</h2>
		</div>
	</a>
	<form class="d-flex col-4">
		<input class="form-control mr-2" type="text">
		<button class="btn btn-outline-secondary" type="submit">Search</button>
	</form>
	<div class="mx-5 userLink">
		<c:if test="${empty userName}">
			<a href="/user/sign-in-view" class="mx-1">로그인</a> 
			<a href="/user/sign-up-view" class="mx-1">회원가입</a>
		</c:if>
		<c:if test="${not empty userName}"> 
			<span>@@@님 안녕하세요</span> 
			<a href="/user/sign-out" class="mx-1">로그아웃</a> 
		</c:if>
		<a href="#" class="mx-1"> <img class="calendar" src="/static/img/calendar.jpg" alt="diary"></a>
	</div>
</div>
