package com.ticketingdiary.diary.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketingdiary.diary.domain.Diary;
import com.ticketingdiary.diary.mapper.DiaryMapper;

@Service
public class DiaryBO {

	@Autowired
	private DiaryMapper diaryMapper;
	
	public Diary findByBookingId(int bookingId) {
		
		return diaryMapper.selectByBookingId(bookingId);
	}
	
	public void addDiary(int bookingId, int userId, String content) {
		diaryMapper.insertDiary(bookingId, userId, content);
	}
	public void updateDiaryByBookingIdUserId(int bookingId, int userId, String content) {

		diaryMapper.updateDiaryByBookingIdUserId(bookingId, userId, content);

	}
	
	
}
