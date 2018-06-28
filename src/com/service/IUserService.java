package com.service;

import java.util.List;

import com.beans.User;

/**
 * 用户的业务层接口
 * @author 杨鑫荣
 *
 */
public interface IUserService {

	/**
	 * 用户登录
	 * @param userName 用户名
	 * @param userPasswd 密码
	 * @return 查询到的用户
	 */
	List<User> userLogin(String userName, String userPasswd);
}
