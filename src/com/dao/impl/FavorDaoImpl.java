package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.beans.Book;
import com.beans.Favor;
import com.beans.User;
import com.dao.IFavorDao;

/**
 * 收藏的持久层实现类
 * @author 杨鑫荣
 *
 */
@Repository("favorDao")
public class FavorDaoImpl implements IFavorDao {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Favor> findAll(DetachedCriteria dCriteria) {
		return (List<Favor>) hibernateTemplate.findByCriteria(dCriteria);
	}

	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	public boolean isFavor(User user, Integer bookId) {
		Integer userId = user.getId();
		List<Favor> result = (List<Favor>) hibernateTemplate.find("FROM Favor WHERE user.id = ? AND book.id = ?", userId, bookId);
		
		return !result.isEmpty();
	}

	@Override
	public void save(Favor favor) {
		hibernateTemplate.save(favor);
	}

	@Override
	public void delete(Favor favor) {
		hibernateTemplate.delete(favor);
	}

}
