package com.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.beans.Favor;
import com.beans.User;

/**
 * 收藏信息的业务层接口
 * @author 杨鑫荣
 *
 */
public interface IFavorService {

	/**
	 * 获取所有收藏列表
	 * @param dCriteria
	 * @return
	 */
	List<Favor> findAllFavorList(DetachedCriteria dCriteria);
	
	/**
	 * 添加收藏
	 * @param favor
	 */
	void saveFavor(Favor favor);
	
	/**
	 * 取消收藏
	 * @param favor
	 */
	void cancelFavor(Favor favor);

	/**
	 * 判断是否收藏
	 * @param user
	 * @param bookId
	 * @return
	 */
	boolean isFavor(User user, Integer bookId);
}
