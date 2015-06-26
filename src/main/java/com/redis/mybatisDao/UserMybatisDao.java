package com.redis.mybatisDao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import com.redis.domain.User;

@Component("userMybatisDao")
public interface UserMybatisDao {

	@Insert("insert into t_user values (#{userId},#{name},#{age})")
	public void addUser(User user);
	
	@Delete("delete from t_user where user_id = #{userId}")
	public void deleteUserById(Integer userId);
	
	@Select("select * from t_user where user_id = #{userId}")
	@Results({
		@Result(id=true, column = "user_id", property = "userId"),
		@Result(column = "user_name", property = "name"),
		@Result(column = "user_age", property = "age"),
	})
	public User getUserById(Integer userId);
}
