package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beans.User;
import com.dao.IUserDao;
import com.service.IUserService;

/**
 * 用户的业务层实现类
 * @author 杨鑫荣
 *
 */
@Service("userService")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class UserServiceImpl implements IUserService {

	@Resource(name="userDao")
	private IUserDao userDao;
	
	@Override
	public List<User> userLogin(String userName, String userPasswd) {
		// TODO Auto-generated method stub
		return userDao.findUser(userName, userPasswd);
	}

}
