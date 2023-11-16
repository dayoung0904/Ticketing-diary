<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ticketing diary</title>

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>

<!-- 내가 만든 스타일시트 -->
<link rel="stylesheet" type="text/css" href="/static/css/style.css">
</head>
<body>
	<div id="wrap">
	
		<header class="bg-warning h-100 d-flex align-items-center justify-content-between">
			<div id="logo" class="text-center mx-5 px-5">
				<h1>Ticket</h1>
				<h2>diary</h2>
			</div>
			<form class="d-flex col-4">
				<input class="form-control mr-2" type="text">
        		<button class="btn btn-outline-secondary" type="submit">Search</button>
			</form>
			<div class="mx-5">
				<a href="#" class="mx-1">로그인</a>
				<a href="#" class="mx-1">회원가입</a>
				<span>@@@님 안녕하세요</span>
				<a href="#" class="mx-1">로그아웃</a>
				<a href="#" class="mx-1">
					<img class="calendar" src="/static/img/calendar.jpg" alt="diary">
				</a>
			</div>
		</header>
		<nav class="bg-info">
			<div class="pt-3 w-100 d-flex align-items-center justify-content-center">
				<ul class="nav nav-fill col-8">
					<li class="nav-item"><a href="#" class="nav-link text-white font-weight-bold">홈</a></li>
					<li class="nav-item"><a href="#" class="nav-link text-white font-weight-bold">연극</a></li>
					<li class="nav-item"><a href="#" class="nav-link text-white font-weight-bold">클래식</a></li>
					<li class="nav-item"><a href="#" class="nav-link text-white font-weight-bold">뮤지컬</a></li>
					<li class="nav-item"><a href="#" class="nav-link text-white font-weight-bold">콘서트</a></li>
					<li class="nav-item"><a href="#" class="nav-link text-white font-weight-bold">나의 티켓</a></li>
				</ul>
			</div>
			<hr class="hrcss"></hr>
		</nav>
		<section class="contents d-flex align-items-center justify-content-center">
			<div class="col-6">
				<div>
					<img src="" alt="show1">
				</div>
				<div>title 1</div>
			</div>
		</section>
		<footer class="bg-warning d-flex align-items-center justify-content-center">
			<div class="text-small text-secondary">Copyright © Ticketing diary 2023</div>
		</footer>
	</div>

</body>
</html>