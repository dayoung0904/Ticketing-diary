<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>주소로 장소 표시하기</title>
</head>
<body>
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
				<div>
					공연장소 : ${show.location}
				</div>
			</div>
		</div>
	</div>
</div>
<div class="text-center my-3"><h3>공연장 정보</h3></div>
<div id="map" class="container" style="width:50%;height:350px;" data-address ='${show.address}'></div>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fe427e9fdb4dd87c85ceb75f445679d3&libraries=services"></script>
<script>

$(document).ready(function(){
	
	
	var address = $('#map').data('address');
	var location = $('#map').data('location');

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(address, function(result, status) {
	
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
	
	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });
	
	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	});  

});
</script>
</body>
</html>