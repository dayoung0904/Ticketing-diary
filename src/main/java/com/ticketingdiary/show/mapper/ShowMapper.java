package com.ticketingdiary.show.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ticketingdiary.show.domain.Show;

@Repository
public interface ShowMapper {

	public List<Show> selectShowListLimit(
			@Param("direction") String direction,
			@Param("standardId") Integer standardId,
			@Param("limit") int limit);
	
	public int selectPostIdBySort(String sort);
	
	public Show selectShowById(int showId);
	
	public List<Show> selectShowByCategory(String category);
	
	public int selectShowTotal();
	
	public List<Show> selectShowPaging(
			@Param("start") int start,
			@Param("pageSize") int pageSize);
}
