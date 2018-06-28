package com.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.beans.Category;

/**
 * 分类信息的持久层接口
 * @author 杨鑫荣
 *
 */
public interface ICategoryDao {

	/**
	 * 获取所有的图书分类列表
	 * @param dCriteria
	 * @return
	 */
	public List<Category> findAll(DetachedCriteria dCriteria);

}
