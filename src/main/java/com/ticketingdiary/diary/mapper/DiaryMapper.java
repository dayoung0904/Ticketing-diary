package com.ticketingdiary.diary.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ticketingdiary.diary.domain.Diary;

@Repository
public interface DiaryMapper {

	public Diary selectByBookingId(int bookingId);
	
	public void insertDiary(
			@Param("bookingId") int bookingId, 
			@Param("userId") int userId, 
			@Param("content") String content);
	
	public void updateDiaryByBookingIdUserId(
			@Param("bookingId") int bookingId, 
			@Param("userId") int userId, 
			@Param("content") String content);	
}
