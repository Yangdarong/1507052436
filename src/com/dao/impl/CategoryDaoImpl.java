package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.beans.Category;
import com.dao.ICategoryDao;

/**
 * 分类信息的持久层实现类
 * @author 杨鑫荣
 *
 */
@Repository("categoryDao")
public class CategoryDaoImpl implements ICategoryDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Category> findAll(DetachedCriteria dCriteria) {
		// TODO Auto-generated method stub
		return (List<Category>) hibernateTemplate.findByCriteria(dCriteria);
	}

}
