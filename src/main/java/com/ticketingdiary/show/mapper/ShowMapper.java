package com.ticketingdiary.show.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ticketingdiary.show.domain.Show;

@Repository
public interface ShowMapper {

	public List<Show> selectShowListLimit();
	
	public Show selectShowById(int showId);
}
