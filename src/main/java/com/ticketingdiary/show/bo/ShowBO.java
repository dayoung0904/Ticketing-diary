package com.ticketingdiary.show.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketingdiary.Paging.domain.Paging;
import com.ticketingdiary.review.bo.ReviewBO;
import com.ticketingdiary.show.domain.Show;
import com.ticketingdiary.show.domain.ShowStar;
import com.ticketingdiary.show.mapper.ShowMapper;

@Service
public class ShowBO {

	@Autowired
	private ShowMapper showMapper;
	
	@Autowired
	private ReviewBO reviewBO;
	
	private static final int POST_MAX_SIZE = 5;
	
	
	//input:showId		output:Show
	public Show getShowById(int showId) {
		return showMapper.selectShowById(showId);
	}
	
	// input:X 		output:List<ShowStar>
	public List<ShowStar> generateShowStarList(Integer prevId, Integer nextId){
		String direction = null; // 방향
		Integer standardId = null; // 기준 showId
		List<ShowStar> showStarList = new ArrayList<>();
		
		if(prevId != null) { // 이전
			direction = "prev";
			standardId = prevId;
		
			List<Show> showList = showMapper.selectShowListLimit(direction, standardId, POST_MAX_SIZE);
			
			for(Show show : showList) {
				ShowStar showStar = new ShowStar();
				
				// show 한개
				showStar.setShow(show);
				
				// 별점
				showStar.setAverageStar(reviewBO.findReviewStarAverage(show.getId()));
				
				showStarList.add(showStar);
			}
			
			Collections.reverse(showStarList); // 뒤집고 저장
			
			return showStarList;
		} else if(nextId != null) { // 다음
			direction = "next";
			standardId = nextId;
		}
		
		List<Show> showList = showMapper.selectShowListLimit(direction, standardId, POST_MAX_SIZE);
		
		for(Show show : showList) {
			ShowStar showStar = new ShowStar();
			
			// show 한개
			showStar.setShow(show);
			
			// 별점
			showStar.setAverageStar(reviewBO.findReviewStarAverage(show.getId()));
			
			showStarList.add(showStar);
		}
		
		return showStarList;
	}
	
	// 이전 페이지의 마지막인가?
	public boolean isPrevLastPage(int prevId) {
		int showId = showMapper.selectPostIdBySort("DESC");
		return showId == prevId; // 같으면 끝 true, 아니면 false
	}
		
	// 다음 페이지의 마지막인가?
	public boolean istNextLastPage(int nextId) {
		int showId = showMapper.selectPostIdBySort("ASC");
		return showId == nextId; // 같으면 끝 true, 아니면 false
	}
	
	
	// input: category		output:List<ShowStar>
	public List<ShowStar> generateShowStarByCategory(String category){
		List<ShowStar> showStarList = new ArrayList<>();
		
		List<Show> showList = showMapper.selectShowByCategory(category);
		
		for(Show show : showList) {
			ShowStar showStar = new ShowStar();
			
			// category의 show 한개
			showStar.setShow(show);
			
			// 별점
			showStar.setAverageStar(reviewBO.findReviewStarAverage(show.getId()));
			
			showStarList.add(showStar);
		}
		return showStarList;
	}
	
	// input:X		output: 전체 show의 갯수
	public int findShowTotal() {
		return showMapper.selectShowTotal();
	}
	
	public int countShow() {
		return showMapper.countShow();
	};
	
	public List<Paging> selectShow(Paging paging){
		return showMapper.selectShow(paging);
	}
}
