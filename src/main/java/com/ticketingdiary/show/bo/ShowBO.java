package com.ticketingdiary.show.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	//input:X		output:List<Show>
	public List<Show> getShowListLimit(){
		return showMapper.selectShowListLimit();
	}
	
	//input:showId		output:Show
	public Show getShowById(int showId) {
		return showMapper.selectShowById(showId);
	}
	
	// input:X 		output:List<ShowStar>
	public List<ShowStar> generateShowStarList(){
		List<ShowStar> showStarList = new ArrayList<>();
		
		List<Show> showList = showMapper.selectShowListLimit();
		
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
}
