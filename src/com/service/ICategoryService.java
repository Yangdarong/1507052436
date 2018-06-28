package com.service;

import com.beans.Category;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 分类信息的业务层接口
 * @author 杨鑫荣
 *
 */
public interface ICategoryService {

	/**
	 * 获取所有分类信息
	 * @param dCriteria
	 * @return
	 */
	List<Category> findAllCategories(DetachedCriteria dCriteria);
}
