package com.redis.redisDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

public class BaseRedisDao {
	@Autowired
    private StringRedisTemplate redisTemplate;
	
	/*
	 * 查看该key是否已经存在于redis数据库中
	 * 存在时返回false
	 * 不存在时返回true
	 */
	public boolean isEmpty(final String key) {
		// TODO Auto-generated method stub
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				// TODO Auto-generated method stub
				return !connection.exists(redisTemplate.getStringSerializer().serialize(key));
			}
			
		});
	}
}
