<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center my-4">
	<div class="login-box">
		<%-- 키보드 엔터로 로그인 가능하게 form태그를 사용 --%>
		<form id="loginForm" action="/user/sign-in" method="post">
		<div class="input-group mb-3">
				<%-- input-group-prepend: input box 앞에 ID 부분을 회색으로 붙인다. --%>
				<div class="input-group-prepend">
					<span class="input-group-text">ID</span>
				</div>
				<input type="text" class="form-control" id="loginId" name="loginId">
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">PW</span>
				</div>
				<input type="password" class="form-control" id="password" name="password">
			</div>

			<%-- btn-block: 로그인 박스 영역에 버튼을 가득 채운다. --%>
			<input type="submit" id="loginBtn" class="btn btn-block btn-primary my-2" value="로그인">
		</form>	
	</div>
</div>
<div class="d-flex justify-content-center">
	<a id="kakao-login-btn" href="javascript:loginWithKakao()">
		<img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" alt="카카오 로그인 버튼" />
	</a>
	<p id="token-result"></p>
	<form id="form-kakao-login" method="post" action="kakao-login">
		<input type="hidden" name="email" /> 
		<input type="hidden" name="name" />
		<input type="hidden" name="phoneNumber" />
	</form>
</div>
<div class="d-flex justify-content-center my-5">
	<div class="signUp-box">
		<h5>아직 회원이 아니시라면?</h5>
		<a class="btn btn-block btn-secondary text-white my-3" href="/user/sign-up-view">회원가입</a>
	</div>
</div>


<script>
$(document).ready(function(){
	$('#loginForm').on('submit', function(e){
		e.preventDefault(); // form submit 중단
		//alert("aaa");
		
		// validation
		let loginId = $('#loginId').val().trim();
		let password = $('#password').val();
		
		if(!loginId){
			alert("아이디를 입력하세요");
			return false;
		}
		if(!password){
			alert("비밀번호를 입력하세요");
			return false;
		}
		
		// AJAX, 
		// url, param
		let url = $(this).attr('action');
		console.log(url);
		let param = $(this).serialize(); 
		console.log(param);
		
		$.post(url, param)
		.done(function(data){
			if(data.code == 200){
				// 성공
				// 홈으로 이동
				location.href = "/show/list-view";
			} else{
				// 로직 상 실패
				alert(data.errorMessage);
			}
		});
	});
	
	$('#kakao-login-btn').on('click', function(){
		location.href = "https://kauth.kakao.com/oauth/authorize?client_id=b37647856d203a399591434efcf628d9&redirect_uri=http://localhost:8080/user/kakao-login&response_type=code";
	});
});

</script>

