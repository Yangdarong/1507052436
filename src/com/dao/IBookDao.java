package com.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.beans.Book;
import com.beans.User;

/**
 * 图书的持久层接口
 * @author 杨鑫荣
 *
 */
public interface IBookDao {

	/**
	 * 获取图书列表(可加条件判断)
	 * @param dCriteria
	 * @return
	 */
	List<Book> findAll(DetachedCriteria dCriteria);

	/**
	 * 通过用户返回收藏列表
	 * @param user
	 * @return
	 */
	List<Book> findByUserFavor(User user);

	/**
	 * 保存书本
	 * @param book
	 */
	void save(Book book);

}
