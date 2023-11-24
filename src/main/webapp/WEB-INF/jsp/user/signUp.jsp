<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="sign-up-box">
		<form id="signUpForm" method="post" action="/user/sign-up">
			<div class="d-flex justify-content-between align-items-center">
				<span class="sign-up-subject">ID</span>
				<%-- 인풋 옆에 중복확인 버튼을 옆에 붙이기 위해 div 만들고 d-flex --%>
				<div class="d-flex ml-3">
					<input type="text" id="loginId" name="loginId" class="form-control col-11" placeholder="ID를 입력해주세요">
					<button type="button" id="loginIdCheckBtn" class="btn btn-primary col-4">중복확인</button>
				</div>
			</div>
			<%-- 아이디 체크 결과 --%>
			<div class="ml-3 mb-3">
				<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
				<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
				<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
			</div>

			<div class="d-flex justify-content-between align-items-center">
				<span class="sign-up-subject">비밀번호</span>
				<div class="m-3">
					<input type="password" id="password" name="password" class="form-control col-12" placeholder="비밀번호를 입력하세요">
				</div>
			</div>
			
			<div class="d-flex justify-content-between align-items-center">
				<span class="sign-up-subject">비밀번호 확인</span>
				<div class="m-3">
					<input type="password" id="confirmPassword" name="confirmPassword" class="form-control col-12" placeholder="비밀번호를 입력하세요">
				</div>
			</div>
			
			<div class="d-flex justify-content-between align-items-center">
				<span class="sign-up-subject">이름</span>
				<div class="m-3">
					<input type="text" id="name" name="name" class="form-control col-12" placeholder="이름을 입력하세요">
				</div>
			</div>
			
			<div class="d-flex justify-content-between align-items-center">
				<span class="sign-up-subject">전화번호</span>
				<div class="m-3">
					<input type="text" id="phoneNumber" name="phoneNumber" class="form-control col-12" placeholder="전화번호를 입력하세요">
				</div>
			</div>
			
			<div class="d-flex justify-content-between align-items-center">
				<span class="sign-up-subject">이메일</span>
				<div class="m-3">
					<input type="text" id="email" name="email" class="form-control col-12" placeholder="이메일을 입력하세요">
				</div>
			</div>
			
			<br>
			<div class="d-flex justify-content-center m-3">
				<button type="submit" id="signUpBtn" class="btn btn-primary col-6">가입하기</button>
			</div>
		</form>
	</div>
</div>
<div class="d-flex justify-content-center my-5">
	<div>
		<h5>이미 회원이시라면?</h5>
		<a class="btn btn-block btn-secondary text-white my-3" href="/user/sign-in-view">로그인</a>	
	</div>
</div>


<script>
$(document).ready(function(){
	// 아이디 중복조회
	$('#loginIdCheckBtn').on('click', function(){
		//alert("click");
		
		// 경고 문구 초기화
		$('#idCheckLength').addClass('d-none');
		$('#idCheckDuplicated').addClass('d-none');
		$('#idCheckOk').addClass('d-none');
		
		let loginId = $('#loginId').val().trim();
		if(loginId.length < 4){
			$('#idCheckLength').removeClass('d-none');
			return;
		}
		
		// AJAX 중복확인
		$.ajax({
			// request
			url:"/user/is-duplicated-id"
			, data:{"loginId":loginId}
			
			// response
			, success:function(data){
				if(data.isDuplicated){ // 중복
					$('#idCheckDuplicated').removeClass('d-none');
				} else{ // 사용가능
					$('#idCheckOk').removeClass('d-none');
				}
			}
			, error:function(request, status, error){
				alert("중복확인에 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
	
	// 회원가입
	$('#signUpForm').on('submit',function(e){
		e.preventDefault();
		///alert("ㅁㅁㅁ");
		
		//validation
		let loginId = $('#loginId').val().trim();
		let password = $('#password').val();
		let confirmPassword = $('#confirmPassword').val();
		let name = $('#name').val().trim();
		let phoneNumber = $('#phoneNumber').val().trim();
		let email = $('#email').val().trim();
		
		if(!loginId){
			alert("로그인 아이디를 입력하세요.");
			return false;
		}
		if(!loginId || !confirmPassword){
			alert("비밀번호를 입력하세요.");
			return false;
		}
		if(loginId != confirmPassword){
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
		if(!name){
			alert("이름을 입력하세요.");
			return false;
		}
		if(!phoneNumber){
			alert("전화번호를 입력하세요.");
			return false;
		}
		if(!email){
			alert("이메일을 입력하세요.");
			return false;
		}
		
		// 중복체크 안되있는 경우
		if($('#idCheckOk').hasClass('d-none')){
			alert("아이디 중복체크를 해주세요.");
			return false;
		}
		
		// ajax
		let url = $(this).attr('action');
		let param = $(this).serialize();
		console.log(url);
		console.log(param);
		
		$.post(url, param) // request
		.done(function(data){ // response
			if(data.code == 200){ // 성공
				alert("가입을 환영합니다. 로그인을 해주세요!");
				location.href = "/user/sign-in-view"; // 로그인 화면으로 이동
			} else{
				// 로직 실패
				alert(data.errorMessage);
			}
		});
		
	});
});

</script>