package com.ticketingdiary.review.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketingdiary.review.domain.Review;
import com.ticketingdiary.review.domain.ShowReview;
import com.ticketingdiary.review.mapper.ReviewMapper;
import com.ticketingdiary.user.bo.UserBO;
import com.ticketingdiary.user.domain.User;

@Service
public class ReviewBO {

	@Autowired
	private ReviewMapper reviewMapper;
	
	@Autowired
	private UserBO userBO;
	
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
	
	// input:showId		output:List<ShowRivew>
	public List<ShowReview> generateShowReview(int showId){
		List<ShowReview> showReviewList = new ArrayList<>();
		
		// review중 showId로 review 리스트를 가지고 온다.
		List<Review> reviewList = reviewMapper.selectReviewByShowId(showId);
		for(Review review : reviewList) {
			ShowReview showReview = new ShowReview();
			
			// user
			User user = userBO.findUserById(review.getUserId());
			showReview.setUser(user);
			
			// review
			showReview.setReview(review);
			
			// list에 저장
			showReviewList.add(showReview);
		}
		return showReviewList;
	}
}
