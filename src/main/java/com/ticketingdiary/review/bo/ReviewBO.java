package com.ticketingdiary.review.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketingdiary.review.domain.Review;
import com.ticketingdiary.review.mapper.ReviewMapper;

@Service
public class ReviewBO {

	@Autowired
	private ReviewMapper reviewMapper;
	
	//input:bookingId, userId, showId, star, comment		output:X
	public void addReview(int bookingId, int userId, int showId, int star, String comment) {
		reviewMapper.insertReview(bookingId, userId, showId, star, comment);
	}
	
	// input:userId, bookingId 		output:Review
	public Review findReviewByUserIdBookingId(int userId, int bookingId) {
		return reviewMapper.selectReviewByUserIdBookingId(userId, bookingId);
	}

	// input:showId		output:double
	public Double findReviewStarAverage(int showId) {
		//show 한개의 총 별점
		double sum = 0;
		List<Integer> starList = reviewMapper.selectStarByShowId(showId);
		for(int i = 0; i < starList.size(); i++) {
			sum += starList.get(i);
		}
		
		//show 한개의 별점을 준 사람의 수
		int count = reviewMapper.selectCountByShowId(showId);
		
		// 평균
		double average = (Math.round(sum/count*10)/10.0);
		return average;
	}
}
