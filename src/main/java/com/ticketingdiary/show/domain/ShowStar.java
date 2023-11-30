package com.ticketingdiary.show.domain;

import lombok.Data;

@Data
public class ShowStar {

	// show 1개
	private Show show;
	
	// 해당 show의 평균 별점
	private double averageStar;
}
