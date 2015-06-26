package com.redis.redisDao;

import com.redis.domain.User;

public interface UserRedisDao {

	public boolean redis_addUser(User user);
	
	public boolean redis_deleteUserById(Integer userId);
	
	public User redis_getUserById(Integer userId);
}
