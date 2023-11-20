package com.ticketingdiary.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ticketingdiary.user.domain.User;

@Repository
public interface UserMapper {

	public List<Map<String, Object>> selectUser();
	
	public User selectUSerByLoginId(String loginId);

	public Integer insertUser(
			@Param("loginId") String loginId, 
			@Param("password") String password, 
			@Param("name") String name, 
			@Param("phoneNumber") String phoneNumber, 
			@Param("email") String email);
	
	public User selectUserByLoginIdPassword(
			@Param("loginId") String loginId, 
			@Param("password") String password);
}
