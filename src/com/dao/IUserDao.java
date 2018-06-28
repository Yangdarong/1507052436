package com.dao;

import java.util.List;

import com.beans.User;

/**
 * 用户的持久层接口
 * @author 杨鑫荣
 *
 */
public interface IUserDao {
	
	/**
	 * 查询指定账号和密码的用户
	 * @param user
	 * @return
	 */
	List<User> findUser(String userName, String userPasswd);

}
