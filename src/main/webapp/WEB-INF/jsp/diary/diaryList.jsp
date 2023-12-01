<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang='en'>
<head>
<!-- jquery CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- fullcalendar CDN -->
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css' rel='stylesheet' />
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<!-- fullcalendar 언어 CDN -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>

  </head>
  <body>
    <div class="calendar-wrap container">
    	<div id='calendar'></div>
    </div>
  </body>
</html>

<script>
(function(){$(function(){// calendar element 취득      
	var calendarEl = $('#calendar')[0];// full-calendar 생성하기      
	var calendar = new FullCalendar.Calendar(calendarEl, {height: '700px', // calendar 높이 설정        
		expandRows: true, // 화면에 맞게 높이 재설정        
		// 해더에 표시할 툴바        
		headerToolbar: {left: 'prev',
			center: 'title',
			right: 'next'},        
		navLinks: false, // 날짜를 선택하면 Day 캘린더나 Week 캘린더로 링크        
		editable: true, // 수정 가능?       
		selectable: false, // 달력 일자 드래그 설정가능        
		nowIndicator: false, // 현재 시간 마크
		dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)        
		locale: 'ko', // 한국어 설정        
		eventAdd: function(obj) { // 이벤트가 추가되면 발생하는 이벤트          
			console.log(obj);},        
		eventChange: function(obj) { // 이벤트가 수정되면 발생하는 이벤트          
			console.log(obj);},        
		eventRemove: function(obj){ // 이벤트가 삭제되면 발생하는 이벤트          
			console.log(obj);},        
		       
		// 이벤트         
		events: "/diary-event"
		,eventClick: function(info) {
			alert(info.event.id);
			window.open("/user/sign-in-view");
		}
		
		
	});      
	// 캘린더 랜더링      
	calendar.render();    
	});  
})();
</script>
		