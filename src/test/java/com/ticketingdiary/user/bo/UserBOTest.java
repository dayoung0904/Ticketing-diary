package com.ticketingdiary.user.bo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest // spring boot 부팅
class UserBOTest {

	@Autowired
	UserBO userBO;
	
	@Transactional // rollback
	@Test
	void 유저추가_테스트() {
		userBO.addUser("test", "aaa", "test", "test", "test");
	}

}
