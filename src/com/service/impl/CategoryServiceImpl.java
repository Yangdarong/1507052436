package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Category;
import com.dao.ICategoryDao;
import com.service.ICategoryService;

/**
 * 分类信息的业务层实现类
 * @author 杨鑫荣
 *
 */
@Service("categoryService")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class CategoryServiceImpl implements ICategoryService {

	@Resource(name="categoryDao")
	private ICategoryDao categoryDao;
	
	@Override
	public List<Category> findAllCategories(DetachedCriteria dCriteria) {
		// TODO Auto-generated method stub
		return categoryDao.findAll(dCriteria);
	}

}
