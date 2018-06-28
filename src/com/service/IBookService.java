package com.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.beans.Book;
import com.beans.User;

/**
 * 书本信息的业务层接口
 * @author 杨鑫荣
 *
 */
public interface IBookService {

	/**
	 * 获取所有课本
	 * @param dCriteria
	 * @return
	 */
	List<Book> findAllBooks(DetachedCriteria dCriteria);

	/**
	 * 查询该用户的收藏信息
	 * @param user
	 * @return
	 */
	List<Book> findByUserFavor(User user);

	/**
	 * 添加书本
	 * @param book
	 */
	void saveBook(Book book);
}
