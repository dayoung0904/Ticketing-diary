package com.ticketingdiary.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// input:user	output:user
	public User loginWithKaKao(User user) {
		User savedUser = userMapper.selectUserByEmail(user.getEmail());
		if(savedUser == null) {
			userMapper.insertUser(user.getLoginId(), user.getPassword(), user.getName(), user.getPhoneNumber(), user.getEmail(), user.getLoginType());
			savedUser = userMapper.selectUserByEmail(user.getEmail());
		}
		return savedUser;
	}
}
