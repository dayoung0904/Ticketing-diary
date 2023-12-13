package com.ticketingdiary.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketingdiary.user.domain.User;
import com.ticketingdiary.user.mapper.UserMapper;

@Service
public class UserBO {

	@Autowired
	private UserMapper userMapper;
	
	// input:loginId		output: User
	public User findUSerByLoginId(String loginId) {
		
		return userMapper.selectUSerByLoginId(loginId);
	}
	
	// input:param		output:Integer(userId)
	public Integer addUser(String loginId, String password, String name, 
			String phoneNumber, String email) {
		String loginType = "";
		return userMapper.insertUser(loginId, password, name, phoneNumber, email, loginType);
	}
	
	// input:loginId,password		output:user
	public User findUserByLoginIdPassword(String loginId, String password) {
		return userMapper.selectUserByLoginIdPassword(loginId, password);
	}
	
	
	// input:email name		output:user
	public User findUserByNameEmail(String name, String email) {
		return userMapper.selectUserByNameEmail(name, email);
	}
	
	public void addKakaoUser(String name, String email) {
		String loginType = "kakao";
		String loginId = "";
		String password = "";
		String phoneNumber = "";
		
		userMapper.insertUser(loginId, password, name, phoneNumber, email, loginType);
	}
	
	// input: id		output:user
	public User findUserById(int id) {
		return userMapper.selectUserById(id);
	}
}
