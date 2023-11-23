package com.ticketingdiary.show.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketingdiary.show.domain.Show;
import com.ticketingdiary.show.mapper.ShowMapper;

@Service
public class ShowBO {

	@Autowired
	private ShowMapper showMapper;
	
	//input:X		output:List<Show>
	public List<Show> getShowListLimit(){
		return showMapper.selectShowListLimit();
	}
	
	//input:showId		output:Show
	public Show getShowById(int showId) {
		return showMapper.selectShowById(showId);
	}
}
