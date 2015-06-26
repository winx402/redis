package com.redis.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.redis.domain.User;
import com.redis.mybatisDao.UserMybatisDao;
import com.redis.redisDao.BaseRedisDao;
import com.redis.redisDao.UserRedisDao;

@Component("userDao")
public class UserDaoImpl extends BaseRedisDao implements UserRedisDao{
	
	@Autowired
    private StringRedisTemplate redisTemplate;
	@Autowired
	private UserMybatisDao userMybatisDao;

	@Override
	public boolean redis_addUser(final User user) {
		// TODO Auto-generated method stub
		//存储redis hashMap对象
		//比较原始、复杂的方法
//		return (Boolean)redisTemplate.execute(new RedisCallback<Object>() {
//			@Override
//			public Object doInRedis(RedisConnection redisConnection)
//					throws DataAccessException {
//				// TODO Auto-generated method stub
//				byte[] key = redisTemplate.getStringSerializer().serialize("u:"+user.getUserId());
//	            BoundHashOperations<Serializable, byte[], byte[]> boundHashOperations = redisTemplate.boundHashOps(key);
//	            boundHashOperations.put(redisTemplate.getStringSerializer()  
//	                    .serialize("userId"), redisTemplate  
//	                    .getStringSerializer().serialize(user.getUserId()+""));  
//	            boundHashOperations.put(redisTemplate.getStringSerializer()  
//	                    .serialize("userName"), redisTemplate  
//	                    .getStringSerializer().serialize(user.getName()));  
//	            boundHashOperations.put(redisTemplate.getStringSerializer()  
//	                    .serialize("userAge"), redisTemplate  
//	                    .getStringSerializer().serialize(user.getAge()+""));  
//	            redisConnection.hMSet(key, boundHashOperations.entries());
//				return true;
//			}
//		});
		HashOperations<String,String,String> hashOperations = redisTemplate.opsForHash();
		hashOperations.put("u:"+user.getUserId(), "userName", user.getName());
		hashOperations.put("u:"+user.getUserId(), "userAge", user.getAge()+"");
		return true;
	}

	@Override
	public boolean redis_deleteUserById(final Integer userId) {
		HashOperations<String,String,String> hashOperations = redisTemplate.opsForHash();
		hashOperations.delete("u:"+userId, "userName","userAge");
		return true;
	}

	@Override
	public User redis_getUserById(Integer userId) {
		User user = new User();
		HashOperations<String,String,String> hashOperations = redisTemplate.opsForHash();
		user.setName(hashOperations.get("u:"+userId,"userName"));
		user.setAge(Integer.parseInt(hashOperations.get("u:"+userId,"userAge")));
		return user;
	}
	
	public void mybatis_addUser(User user) {
		userMybatisDao.addUser(user);
	}

	public void mybatis_deleteUserById(Integer userId) {
		userMybatisDao.deleteUserById(userId);
	}

	public User mybatis_getUserById(Integer userId) {
		return userMybatisDao.getUserById(userId);
	}

}
