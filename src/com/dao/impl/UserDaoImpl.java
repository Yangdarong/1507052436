package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.beans.User;
import com.dao.IUserDao;

/**
 * 用户的持久层实现类
 * @author 杨鑫荣
 *
 */
@Repository("userDao")
public class UserDaoImpl implements IUserDao{

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<User> findUser(String userName, String userPasswd) {
		return (List<User>) hibernateTemplate.find("FROM User WHERE name = ? AND passwd = ?",
				userName, userPasswd);
	}
	
	
}
