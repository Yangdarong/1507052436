package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Book;
import com.beans.User;
import com.dao.IBookDao;
import com.service.IBookService;

@Service("bookService")
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class BookServiceImpl implements IBookService {

	@Resource(name="bookDao")
	private IBookDao bookDao;
	
	@Override
	public List<Book> findAllBooks(DetachedCriteria dCriteria) {
		// TODO Auto-generated method stub
		return bookDao.findAll(dCriteria);
	}

	@Override
	public List<Book> findByUserFavor(User user) {
		// TODO Auto-generated method stub
		return bookDao.findByUserFavor(user);
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void saveBook(Book book) {
		bookDao.save(book);
	}

}
