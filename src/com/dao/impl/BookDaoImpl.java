package com.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.beans.Book;
import com.beans.User;
import com.dao.IBookDao;

/**
 * 图书的持久层实现类
 * @author 杨鑫荣
 *
 */
@Repository("bookDao")
public class BookDaoImpl implements IBookDao {
	
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<Book> findAll(DetachedCriteria dCriteria) {
		// TODO Auto-generated method stub
		return (List<Book>) hibernateTemplate.findByCriteria(dCriteria);
	}

	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Book> findByUserFavor(User user) {
		// TODO Auto-generated method stub
		Integer userId = user.getId();
		return (List<Book>) hibernateTemplate.find("SELECT b FROM Favor f JOIN Book b ON "
				+ "f.book.id = b.id AND f.user.id = ?", userId);
	}

	@Override
	public void save(Book book) {
		// TODO Auto-generated method stub
		hibernateTemplate.save(book);
	}

}
