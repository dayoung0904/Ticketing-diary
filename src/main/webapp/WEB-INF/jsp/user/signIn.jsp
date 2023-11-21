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
		<a class="btn btn-block btn-secondary text-white my-3" href="#">회원가입</a>
		<a class="btn btn-block btn-warning text-white my-3" href="#">카카오 회원가입</a>
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
});

</script>

<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.5.0/kakao.min.js"
  integrity="sha384-kYPsUbBPlktXsY6/oNHSUDZoTX6+YI51f63jCPEIPFP09ttByAdxd2mEjKuhdqn4" crossorigin="anonymous">
</script>
<script>
  Kakao.init('f729b84b0bebd8d1aed56ea6bfa7b954'); // 사용하려는 앱의 JavaScript 키 입력
</script>

<script>
//카카오로그인
$(document).ready(function(){
	$('#kakao-login-btn').on('click', function(e) {
		// alert("aaa");
		e.preventDefault();
	function kakaoLogin() {
			Kakao.Auth.login({
				success : function(auth) {
					Kakao.API.request({
						url : '/v2/user/me',
						success : function(response) {
							// 사용자 정보 추출
							var account = response.kakao_account;
							
							$('#form-kakao-login input[name=email]').val(account.email);
							$('#form-kakao-login input[name=name]').val(account.name);
							$('#form-kakao-login input[name=phoneNumber]').val(account.phone_number);
							// 사용자 정보가 포함된 폼을 서버로 제출한다.
							document.querySelector('#form-kakao-login').submit();
							
						},
						fail : function(error) {
							alert("카카오로그인 처리 중 오류가 발생했습니다.");
						},
					})
				},
				fail : function(error) {
					alert("카카오로그인 처리 중 오류가 발생했습니다. 관리자에게 문의해주세요.");
				},
			})
		}
	});
});
</script>