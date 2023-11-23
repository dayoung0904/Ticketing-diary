<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="d-flex justify-content-center">
	<table class="w-75">
		<thead class="text-center">
			<tr>
				<th>예매일</th>
				<th>공연정보</th>
				<th>예매정보</th>
				<th>상태</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>20XX.X.XX</td>
				<td>
					<div class="d-flex">
						<div class="col-3">
							<img src="#" alt="공연 포스터">
						</div>
						<div class="col-7">
							<div class="mb-3"><a href="/booking/booking-review-view?bookingId="bookingId">title1</a></div>
							<div>공연기간:20XX.X.XX~20XX.X.XX</div>
							<div>공연장소:예술의 전당 IBK홀</div>
						</div>
					</div>
				</td>
				<td>
					<div>관람일:20XX.X.XX</div>
					<div class="mb-3">매수:1석</div>
					<div>취소가능일:20XX.X.XX</div>
				</td>
				<td>예매확인</td>
			</tr>
		</tbody>
	</table>
</div>
