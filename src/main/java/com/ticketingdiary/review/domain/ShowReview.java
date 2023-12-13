package com.ticketingdiary.review.domain;

import com.ticketingdiary.user.domain.User;

import lombok.Data;

@Data
public class ShowReview {

	// 해당 show에 리뷰를 작성한 user
	private User user;
	
	// 해당 user의 review
	private Review review;
}
