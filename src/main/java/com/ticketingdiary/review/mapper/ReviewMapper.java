package com.ticketingdiary.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ticketingdiary.review.domain.Review;

@Repository
public interface ReviewMapper {

	public void insertReview(
			@Param("bookingId") int bookingId, 
			@Param("userId") int userId, 
			@Param("showId") int showId, 
			@Param("star") int star, 
			@Param("comment") String comment);
	
	public Review selectReviewByUserIdBookingId(
			@Param("userId") int userId, 
			@Param("bookingId") int bookingId);
	
	public List<Integer> selectStarByShowId(int showId);
	
	public int selectCountByShowId(int showId);
}
