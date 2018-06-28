package com.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.beans.Favor;
import com.beans.User;

/**
 * 收藏的持久层实现类
 * @author 杨鑫荣
 *
 */
public interface IFavorDao {

	/**
	 * 获取所有分类信息
	 * @param dCriteria
	 * @return
	 */
	List<Favor> findAll(DetachedCriteria dCriteria);

	/**
	 * 判断是否搜藏该书本
	 * @param user
	 * @param bookId
	 * @return
	 */
	boolean isFavor(User user, Integer bookId);

	/**
	 * 添加收藏
	 * @param favor
	 */
	void save(Favor favor);

	/**
	 * 取消收藏
	 * @param favor
	 */
	void delete(Favor favor);

}
