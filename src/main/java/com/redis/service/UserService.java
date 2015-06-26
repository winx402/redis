package com.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redis.dao.impl.UserDaoImpl;
import com.redis.domain.User;

@Service("userService")
public class UserService {

	@Autowired
	private UserDaoImpl userDao;
	
	public void addUser(User user) {
		userDao.redis_addUser(user);
	}

	public void deleteUserById(Integer userId) {
		userDao.redis_deleteUserById(userId);
	}

	public User getUserNameById(Integer userId) {
		return userDao.redis_getUserById(userId);
	}
}
